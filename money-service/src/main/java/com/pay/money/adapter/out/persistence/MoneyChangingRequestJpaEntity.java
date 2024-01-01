package com.pay.money.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "money_changing_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChangingRequestJpaEntity {

    @Id
    @GeneratedValue
    private Long moneyChangingRequestId;

    private String targetMembershipId;

    private int moneyChangingType; // 0: 증액, 1: 감액

    private int moneyAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private int changingMoneyStatus; // 0: 요청, 1: 성공, 2: 실패

    private UUID uuid;

    public MoneyChangingRequestJpaEntity(String targetMembershipId, int moneyChangingType, int moneyAmount, Timestamp timestamp, int changingMoneyStatus, UUID uuid) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingType = moneyChangingType;
        this.moneyAmount = moneyAmount;
        this.timestamp = timestamp;
        this.changingMoneyStatus = changingMoneyStatus;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "MoneyChangingRequestJpaEntity{" +
                "moneyChangingRequestId=" + moneyChangingRequestId +
                ", targetMembershipId='" + targetMembershipId + '\'' +
                ", moneyChangingType=" + moneyChangingType +
                ", moneyAmount=" + moneyAmount +
                ", timestamp=" + timestamp +
                ", changingMoneyStatus=" + changingMoneyStatus +
                '}';
    }
}
