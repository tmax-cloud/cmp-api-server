package com.tmax.cmp.entity.aws.ec2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ElasticGpuAssociation {

    private String elasticGpuId;

    private String elasticGpuAssociationId;

    private String elasticGpuAssociationState;

    private String elasticGpuAssociationTime;
}
