package com.tmax.cmp.entity.aws.ec2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.ec2.model.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;


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

    @Embedded
    @AttributeOverride(name = "state", column = @Column(name = "monitoring_state"))
    private Monitoring monitoring;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "availabilityZone", column = @Column(name = "placement_availability_zone")),
            @AttributeOverride(name = "affinity", column = @Column(name = "placement_affinity")),
            @AttributeOverride(name = "groupName", column = @Column(name = "placement_group_name")),
            @AttributeOverride(name = "partitionNumber", column = @Column(name = "placement_partition_number")),
            @AttributeOverride(name = "hostId", column = @Column(name = "placement_host_id")),
            @AttributeOverride(name = "tenancy", column = @Column(name = "placement_tenancy")),
            @AttributeOverride(name = "spreadDomain", column = @Column(name = "placement_spread_domain")),
            @AttributeOverride(name = "hostResourceGroupArn", column = @Column(name = "placement_host_resource_group_arn"))})
    private Placement placement;

    private String platform;

    private String privateDnsName;

    private String privateIpAddress;

    @ElementCollection
    @CollectionTable(name="ProductCode", joinColumns = @JoinColumn(name = "instanceId"))
    private List<ProductCode> productCodes;

    private String publicDnsName;

    private String publicIpAddress;

    private String ramdiskId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "state_reason_code")),
            @AttributeOverride(name = "message", column = @Column(name = "state_reason_message"))})
    private StateReason stateReason;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "state_code")),
            @AttributeOverride(name = "name", column = @Column(name = "state_name"))})
    private InstanceState state;

    @Column(columnDefinition = "varchar(500)")
    private String stateTransitionReason;

    private String subnetId;

    private String vpcId;

    private String architecture;

    @ElementCollection
    @CollectionTable(name="InstanceBlockDeviceMapping", joinColumns = @JoinColumn(name = "instanceId"))
    private List<InstanceBlockDeviceMapping> blockDeviceMappings;

    private String clientToken;

    private Boolean ebsOptimized;

    private Boolean enaSupport;

    private String hypervisor;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "arn", column = @Column(name = "iam_instance_profile_arn")),
            @AttributeOverride(name = "id", column = @Column(name = "iam_instance_profile_id"))})
    private IamInstanceProfile iamInstanceProfile;

    private String instanceLifecycle;

    @ElementCollection
    @CollectionTable(name="ElasticGpuAssociation", joinColumns = @JoinColumn(name = "instanceId"))
    private List<ElasticGpuAssociation> elasticGpuAssociation;

    @ElementCollection
    @CollectionTable(name="ElasticInferenceAcceleratorAssociation", joinColumns = @JoinColumn(name = "instanceId"))
    private List<ElasticInferenceAcceleratorAssociation> elasticInferenceAcceleratorAssociations;

    @ElementCollection
    @CollectionTable(name="InstanceNetworkInterface", joinColumns = @JoinColumn(name = "instanceId"))
    private List<InstanceNetworkInterface> networkInterfaces;

    private String outpostArn;

    private String rootDeviceName;

    private String rootDeviceType;

    @ElementCollection
    @CollectionTable(name="GroupIdentifier", joinColumns = @JoinColumn(name = "instanceId"))
    private List<GroupIdentifier> securityGroups;

    private Boolean sourceDestCheck;

    private String spotInstanceRequestId;

    private String sriovNetSupport;

    @ElementCollection
    @CollectionTable(name="Tags", joinColumns = @JoinColumn(name = "instanceId"))
    private List<Tag> tags;

    private String virtualizationType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "coreCount", column = @Column(name = "cpu_options_core_count")),
            @AttributeOverride(name = "threadsPerCore", column = @Column(name = "cpu_options_threads_per_core"))})
    private CpuOptions cpuOptions;

    private String capacityReservationId;

    @Embedded
    private CapacityReservationSpecificationResponse capacityReservationSpecification;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "configured", column = @Column(name = "hibernation_options_configured"))})
    private HibernationOptions hibernationOptions;

    @ElementCollection
    @CollectionTable(name="LicenseConfiguration", joinColumns = @JoinColumn(name = "instanceId"))
    private List<LicenseConfiguration> licenses;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "state", column = @Column(name = "metadata_options_state")),
            @AttributeOverride(name = "httpTokens", column = @Column(name = "metadata_options_http_tokens")),
            @AttributeOverride(name = "httpPutResponseHopLimit", column = @Column(name = "metadata_options_http_put_response_hop_limit")),
            @AttributeOverride(name = "httpEndpoint", column = @Column(name = "metadata_options_http_endpoint")),
            @AttributeOverride(name = "httpProtocolIpv6", column = @Column(name = "metadata_options_http_protocol_ipv6")),
            @AttributeOverride(name = "instanceMetadataTags", column = @Column(name = "metadata_options_instance_metadata_tags"))})
    private InstanceMetadataOptionsResponse metadataOptions;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "enabled", column = @Column(name = "enclave_options_enabled"))})
    private EnclaveOptions enclaveOptions = new EnclaveOptions();

    private String bootMode;

    private String region;
}