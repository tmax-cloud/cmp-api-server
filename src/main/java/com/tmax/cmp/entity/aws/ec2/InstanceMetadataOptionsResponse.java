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
public class InstanceMetadataOptionsResponse {

    private String state;

    private String httpTokens;

    private Integer httpPutResponseHopLimit;

    private String httpEndpoint;

    private String httpProtocolIpv6;

    private String instanceMetadataTags;
}
