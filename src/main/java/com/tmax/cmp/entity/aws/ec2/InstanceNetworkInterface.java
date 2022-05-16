package com.tmax.cmp.entity.aws.ec2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InstanceNetworkInterface {


    @Embedded
    private InstanceNetworkInterfaceAssociation association;

    @Embedded
    @AttributeOverride(name = "status", column = @Column(name = "instance_network_interface_attachment_status"))
    private InstanceNetworkInterfaceAttachment attachment;

    private String description;

//    @ElementCollection
//    @CollectionTable(name="GroupIdentifier", joinColumns = @JoinColumn(name = "instanceId"))
//    private List<GroupIdentifier> groups;

//    private List<InstanceIpv6Address> ipv6Addresses;

    private String macAddress;

    private String networkInterfaceId;

    private String ownerId;

    private String privateDnsName;

    private String privateIpAddress;

//    private List<InstancePrivateIpAddress> privateIpAddresses;

    private Boolean sourceDestCheck;

    private String status;

    private String subnetId;

    private String vpcId;

    private String interfaceType;

//    private List<InstanceIpv4Prefix> ipv4Prefixes;

//    private List<InstanceIpv6Prefix> ipv6Prefixes;

}
