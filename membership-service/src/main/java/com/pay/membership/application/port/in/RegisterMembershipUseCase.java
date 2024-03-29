package com.pay.membership.application.port.in;

import com.pay.common.UseCase;
import com.pay.membership.domain.Membership;

@UseCase
public interface RegisterMembershipUseCase {

    Membership registerMembership(RegisterMembershipCommand command);
}
