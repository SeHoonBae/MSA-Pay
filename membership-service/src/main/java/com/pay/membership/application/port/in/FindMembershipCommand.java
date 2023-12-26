package com.pay.membership.application.port.in;

import com.pay.membership.adapter.in.web.FindMembershipController;
import common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {

    private final String membershipId;

}
