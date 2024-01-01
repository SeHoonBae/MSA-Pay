package com.pay.banking.application.port.out;

import com.pay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.pay.banking.adapter.out.external.bank.FirmbankingResult;
import com.pay.banking.domain.FirmbankingRequest;

public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}
