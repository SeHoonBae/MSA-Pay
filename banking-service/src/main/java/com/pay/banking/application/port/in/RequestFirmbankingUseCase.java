package com.pay.banking.application.port.in;

import com.pay.banking.domain.FirmbankingRequest;

public interface RequestFirmbankingUseCase {
    FirmbankingRequest requestFirmbanking(RequestFirmbankingRequestCommand command);
}
