package com.tmax.cmp.entity.aws.ec2;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CapacityReservationTargetResponse {

    private String capacityReservationId;
    private String capacityReservationResourceGroupArn;
}
