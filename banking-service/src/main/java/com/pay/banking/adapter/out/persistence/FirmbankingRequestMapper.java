package com.pay.banking.adapter.out.persistence;

import com.pay.banking.domain.FirmbankingRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FirmbankingRequestMapper {

    public FirmbankingRequest mapToDomainEntity(FirmbankingRequestJpaEntity requestFirmbankingJpaEntity, UUID uuid){
        return FirmbankingRequest.generateRegisteredBankAccount(
                new FirmbankingRequest.FirmbankingRequestId(requestFirmbankingJpaEntity.getRequestFirmbankingId()+""),
                new FirmbankingRequest.FromBankName(requestFirmbankingJpaEntity.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(requestFirmbankingJpaEntity.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(requestFirmbankingJpaEntity.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(requestFirmbankingJpaEntity.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(requestFirmbankingJpaEntity.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(requestFirmbankingJpaEntity.getFirmbankingStatus()),
                uuid
        );
    }

}
