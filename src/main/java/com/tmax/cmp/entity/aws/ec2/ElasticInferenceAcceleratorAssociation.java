package com.tmax.cmp.entity.aws.ec2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.time.Instant;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ElasticInferenceAcceleratorAssociation {

    private String elasticInferenceAcceleratorArn;

    private String elasticInferenceAcceleratorAssociationId;

    private String elasticInferenceAcceleratorAssociationState;

    private Instant elasticInferenceAcceleratorAssociationTime;
}
