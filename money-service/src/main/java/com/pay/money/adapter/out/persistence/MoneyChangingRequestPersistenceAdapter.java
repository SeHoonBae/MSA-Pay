package com.pay.money.adapter.out.persistence;

import com.pay.common.PersistenceAdapter;
import com.pay.money.application.port.out.IncreaseMoneyPort;
import com.pay.money.domain.MemberMoney;
import com.pay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort {

    private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;
    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MoneyChangingRequestJpaEntity createMoneyChangingRequest(MoneyChangingRequest.TargetMembershipId targetMembershipId, MoneyChangingRequest.MoneyChangingType moneyChangingType, MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount, MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus, MoneyChangingRequest.Uuid uuid) {

        return moneyChangingRequestRepository.save(
                new MoneyChangingRequestJpaEntity(
                        targetMembershipId.getTargetMembershipId(),
                        moneyChangingType.getChangingType(),
                        changingMoneyAmount.getChangingMoneyAmount(),
                        new Timestamp(System.currentTimeMillis()),
                        moneyChangingStatus.getChangingMoneyStatus(),
                        UUID.randomUUID()
                )
        );

    }

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId membershipId, int increaseMoneyAmount) {

        MemberMoneyJpaEntity entity;

        try {
            log.info("start membershipId >>> {}", membershipId.getMembershipId());
            log.info("start increaseMoneyAmount >>> {}", increaseMoneyAmount);

            Long membershipId1 = Long.valueOf(membershipId.getMembershipId());
            log.info("membershipId1 >>> {}", membershipId1);

            List<MemberMoneyJpaEntity> entityList = memberMoneyRepository.findByMembershipId(membershipId1);
            log.info("entity >>> ");
            log.info("entityList Size >>> {}", entityList.size());
            entity = entityList.get(0);
            log.info("{}",entity.getMembershipId());

            entity.setBalance(entity.getBalance() + increaseMoneyAmount);

            return memberMoneyRepository.save(entity);
        }catch (Exception e){
            entity = new MemberMoneyJpaEntity(
                    Long.parseLong(membershipId.getMembershipId()),
                    increaseMoneyAmount
            );

            entity = memberMoneyRepository.save(entity);

            return entity;
        }

    }
}
