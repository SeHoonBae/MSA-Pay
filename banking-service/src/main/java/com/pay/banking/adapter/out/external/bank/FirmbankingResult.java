package com.pay.banking.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FirmbankingResult {

    private int resultCode; // 0: 성공, 1: 실패

}
