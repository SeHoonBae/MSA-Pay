package com.pay.membership.application.port.in;

import com.pay.common.UseCase;
import com.pay.membership.domain.Membership;

@UseCase
public interface FindMembershipUseCase {

    Membership findMembership(FindMembershipCommand command);

}
