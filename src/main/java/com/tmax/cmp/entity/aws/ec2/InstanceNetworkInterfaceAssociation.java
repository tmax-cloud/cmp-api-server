package com.tmax.cmp.entity.aws.ec2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InstanceNetworkInterfaceAssociation {

    private String carrierIp;

    private String customerOwnedIp;

    private String ipOwnerId;

    private String publicDnsName;

    private String publicIp;
}
