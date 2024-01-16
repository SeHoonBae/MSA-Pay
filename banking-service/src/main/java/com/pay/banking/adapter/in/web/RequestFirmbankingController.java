package com.pay.banking.adapter.in.web;

import com.pay.banking.application.port.in.RequestFirmbankingRequestCommand;
import com.pay.banking.application.port.in.RequestFirmbankingUseCase;
import com.pay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmbankingController {

    private final RequestFirmbankingUseCase requestFirmbankingUseCase;

    @PostMapping("/banking/firmbanking/request")
    void registeredBankAccount(@RequestBody RequestFirmbankingRequest request){

        RequestFirmbankingRequestCommand command = RequestFirmbankingRequestCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getAmount())
                .build();

        requestFirmbankingUseCase.requestFirmbanking(command);
    }

}
