package com.tmax.cmp.entity.aws.ec2;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Placement {

    private String availabilityZone;

    private String affinity;

    private String groupName;

    private Integer partitionNumber;

    private String hostId;

    private String tenancy;

    private String spreadDomain;

    private String hostResourceGroupArn;
}
