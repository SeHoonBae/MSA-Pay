package com.pay.money.adapter.in.web;

import com.pay.membership.common.WebAdapter;
import com.pay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.pay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.pay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestMoneyChangingController {

    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;
//    private final DecreaseMoneyRequestUseCase decreaseMoneyRequestUseCase;



    @PostMapping("/money/increase")
    MoneyChangingResultDetail increaseMonyChangingRequest(@RequestBody IncreaseMoneyChangingRequest request){


        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command);

        // MoneyChangingRequest -> MoneyChangingResultDetail
        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                0,
                0,
                moneyChangingRequest.getChangingMoneyAmount()
        );


        return null;
    }

    @PostMapping("/money/decrease")
    MoneyChangingResultDetail decreaseMonyChangingRequest(@RequestBody DecreaseMoneyChangingRequest request){


        return null;
    }

}
