package com.tmax.cmp.entity.aws.ec2;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CapacityReservationSpecificationResponse {

    private String capacityReservationPreference;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name= "capacityReservationId", column = @Column(name = "preference_capacity_reservation_id"))})
    private CapacityReservationTargetResponse capacityReservationTarget;

}
