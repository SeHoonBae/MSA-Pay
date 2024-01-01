package com.pay.membership.application.port.in;

import com.pay.membership.common.UseCase;
import com.pay.membership.domain.Membership;

@UseCase
public interface ModifyMembershipUseCase {

    Membership modifyMembership(ModifyMembershipCommand command);
}
