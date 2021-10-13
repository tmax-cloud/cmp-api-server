package com.tmax.cmp.dto;

import com.amazonaws.services.ec2.model.*;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@Table(name = "AWSInstance")
@NoArgsConstructor
@AllArgsConstructor
public class AWSInstanceDTO {
    private Integer amiLaunchIndex;

    private String imageId;

    @Id
    private String instanceId;

    private String instanceType;

    private String kernelId;

    private String keyName;

    private java.util.Date launchTime;

    private Monitoring monitoring;

    private Placement placement;

    private String platform;

    private String privateDnsName;

    private String privateIpAddress;

    private com.amazonaws.internal.SdkInternalList<ProductCode> productCodes;

    private String publicDnsName;

    private String publicIpAddress;

    private String ramdiskId;

    private InstanceState state;

    private String stateTransitionReason;

    private String subnetId;

    private String vpcId;

    private String architecture;

    private com.amazonaws.internal.SdkInternalList<InstanceBlockDeviceMapping> blockDeviceMappings;

    private String clientToken;

    private Boolean ebsOptimized;

    private Boolean enaSupport;

    private String hypervisor;

    private IamInstanceProfile iamInstanceProfile;

    private String instanceLifecycle;

    private com.amazonaws.internal.SdkInternalList<ElasticGpuAssociation> elasticGpuAssociations;

    private com.amazonaws.internal.SdkInternalList<ElasticInferenceAcceleratorAssociation> elasticInferenceAcceleratorAssociations;

    private com.amazonaws.internal.SdkInternalList<InstanceNetworkInterface> networkInterfaces;

    private String outpostArn;

    private String rootDeviceName;

    private String rootDeviceType;

    private com.amazonaws.internal.SdkInternalList<GroupIdentifier> securityGroups;

    private Boolean sourceDestCheck;

    private String spotInstanceRequestId;

    private String sriovNetSupport;

    private StateReason stateReason;

    private com.amazonaws.internal.SdkInternalList<Tag> tags;

    private String virtualizationType;

    private CpuOptions cpuOptions;

    private String capacityReservationId;

    private CapacityReservationSpecificationResponse capacityReservationSpecification;

    private HibernationOptions hibernationOptions;

    private com.amazonaws.internal.SdkInternalList<LicenseConfiguration> licenses;

    private InstanceMetadataOptionsResponse metadataOptions;

    private EnclaveOptions enclaveOptions;

    private String bootMode;
}