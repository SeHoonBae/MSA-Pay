package com.pay.banking.adapter.out.persistence;

import com.pay.banking.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {

    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountJpaEntity jpaEntity){
        return RegisteredBankAccount.generateRegisteredBankAccount(
                new RegisteredBankAccount.RegisteredBankAccountId(jpaEntity.getRegisteredBankAccountId()+""),
                new RegisteredBankAccount.MembershipId(jpaEntity.getMembershipId()),
                new RegisteredBankAccount.BankName(jpaEntity.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(jpaEntity.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(jpaEntity.isLinkedStatusIsValid())

        );
    }

}
