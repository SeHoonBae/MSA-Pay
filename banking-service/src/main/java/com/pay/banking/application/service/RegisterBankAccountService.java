package com.pay.banking.application.service;

import com.pay.banking.adapter.out.external.bank.BankAccount;
import com.pay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.pay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.pay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.pay.banking.application.port.in.RegisterBankAccountCommand;
import com.pay.banking.application.port.in.RegisterBankAccountUseCase;
import com.pay.banking.application.port.out.RegisterBankAccountPort;
import com.pay.banking.application.port.out.RequestBankAccountInfoPort;
import com.pay.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

        // 은행 계좌를 등록해야 하는 서비스 (비즈니스 로직)
        // command.getMembershipId() 도 유효한지 membership에게 물어봐야 함

        // 멤버 서비스도 확인? 여기서는 skip

        // 1. 외부 실제 은행에 등록이 가능한 계좌인지(정상인지) 확인한다.
        // 외부의 은행에 이 계좌 정상인지? 확인을 해야 함
        // Biz Logic -> External System
        // Port -> Adapter -> External System
        // Port

        command.getBankName();
        command.getBankAccountNumber();


        // 실제 외부의 은행계좌 정보를 Get
        BankAccount accountInfo = requestBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));

        boolean accountIsValid = accountInfo.isValid();

        // 2. 등록가능한 계좌라면, 등록한다. 성공하면, 등록에 성공한 정보를 리턴
        // 2-1. 등록가능하지 않은 계좌라면 에러를 리턴
        if(accountIsValid){

            RegisteredBankAccountJpaEntity savedAccountInfo = registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId() + ""),
                    new RegisteredBankAccount.BankName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid())
            );
            return registeredBankAccountMapper.mapToDomainEntity(savedAccountInfo);

        }else{
            return null;
        }

    }
}
