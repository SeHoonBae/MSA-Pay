package com.pay.money.application.port.out;

import com.pay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.pay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.pay.money.domain.MemberMoney;
import com.pay.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyPort {

    MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.MoneyChangingType moneyChangingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus,
            MoneyChangingRequest.Uuid uuid
    );

    MemberMoneyJpaEntity increaseMoney(
            MemberMoney.MembershipId membershipId,
            int increaseMoneyAmount
    );
}
