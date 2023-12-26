package com.pay.membership.application.port.out;

import com.pay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.pay.membership.domain.Membership;

public interface FindMembershipPort {

    MembershipJpaEntity findMembership(Membership.MembershipId membershipId);

}
