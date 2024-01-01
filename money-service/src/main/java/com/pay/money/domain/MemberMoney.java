package com.pay.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoney {

    @Getter private final String memberMoneyId;

    @Getter private final String membershipId;

    // 잔액
    @Getter private final int balance;

//    @Getter private final int linkedBankAccount;

    public static MemberMoney generateMoneyChangingRequest(
            MemberMoney.MemberMoneyId memberMoneyId,
            MemberMoney.MembershipId membershipId,
            MemberMoney.Balance balance
    ){
        return new MemberMoney(
                memberMoneyId.getMemberMoneyId(),
                membershipId.getMembershipId(),
                balance.getBalance()
        );
    }

    @Value
    public static class MemberMoneyId{
        public MemberMoneyId(String value){
            this.memberMoneyId = value;
        }

        String memberMoneyId;
    }

    @Value
    public static class MembershipId{
        public MembershipId(String value){
            this.membershipId = value;
        }

        String membershipId;
    }
    @Value
    public static class Balance{
        public Balance(int value){
            this.balance = value;
        }

        int balance;
    }


}
