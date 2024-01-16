package com.pay.banking;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.pay.common") // common 패키지 내의 모두를 bean으로 등록을 하고 시작을 하겠다는 의미
public class BankingConfig {



}
