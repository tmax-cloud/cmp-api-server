package com.tmax.cmp.entity.aws.ec2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.ec2.model.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;


@Entity
@Builder
@Getter
@Table(name = "AWSInstance")
@NoArgsConstructor
@AllArgsConstructor
public class Ec2Instance {
    private Integer amiLaunchIndex;

    private String imageId;

    @Id
    private String instanceId;

    private String instanceType;

    private String kernelId;

    private String keyName;

    private Instant launchTime;

    private Monitoring monitoring;

    @Column(columnDefinition = "varchar(1000)")
    private Placement placement;

    private String platform;

    private String privateDnsName;

    private String privateIpAddress;

//    private List<ProductCode> productCodes;

    private String publicDnsName;

    private String publicIpAddress;

    private String ramdiskId;

    private StateReason stateReason;

    private String state;

    @Column(columnDefinition = "varchar(500)")
    private String stateTransitionReason;

    private String subnetId;

    private String vpcId;

    private String architecture;

//    private List<InstanceBlockDeviceMapping> blockDeviceMappings;

    private String clientToken;

    private Boolean ebsOptimized;

    private Boolean enaSupport;

    private String hypervisor;

    @Column(columnDefinition = "varchar(500)")
    private IamInstanceProfile iamInstanceProfile;

    private String instanceLifecycle;

//    private List<ElasticGpuAssociation> elasticGpuAssociation;
//
//    private List<ElasticInferenceAcceleratorAssociation> elasticInferenceAcceleratorAssociations;
//
//    private List<InstanceNetworkInterface> networkInterfaces;

    private String outpostArn;

    private String rootDeviceName;

    private String rootDeviceType;

//    private List<GroupIdentifier> securityGroups;

    private Boolean sourceDestCheck;

    private String spotInstanceRequestId;

    private String sriovNetSupport;

//    private List<Tag> tags;

    private String virtualizationType;

    @Column(columnDefinition = "varchar(500)")
    private CpuOptions cpuOptions;

    private String capacityReservationId;

    @Column(columnDefinition = "varchar(1000)")
    private CapacityReservationSpecificationResponse capacityReservationSpecification;

    @Column(columnDefinition = "varchar(500)")
    private HibernationOptions hibernationOptions;

//    private List<LicenseConfiguration> licenses;

    @Column(columnDefinition = "varchar(1000)")
    private InstanceMetadataOptionsResponse metadataOptions;

    @Column(columnDefinition = "varchar(500)")
    private EnclaveOptions enclaveOptions;

    private String bootMode;

    private String region;
}