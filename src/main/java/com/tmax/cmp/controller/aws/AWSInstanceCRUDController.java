package com.tmax.cmp.controller.aws;

import com.tmax.cmp.configuration.ClientConfig;
import com.tmax.cmp.entity.aws.ec2.*;
import com.tmax.cmp.entity.common.client.awsClient;
import com.tmax.cmp.entity.common.client.vSphereClient;
import com.tmax.cmp.svc.aws.AWSInstanceService;
import com.vmware.vcenter.VMTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.Reservation;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/aws/instance")

public class AWSInstanceCRUDController {

    @Autowired
    private AWSInstanceService awsInstanceService;

    @GetMapping("/sync")
    public void syncInstance(@RequestParam(name = "region", required = false) String region) throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        List<awsClient> clients = (ArrayList<awsClient>)context.getBean("awsClients");

        for(awsClient client : clients) {
            for (Ec2Client ec2Client : client.getEc2Clients()) {
                ArrayList<Ec2Instance> instanceList = awsInstanceService.getAllInstanceFromAWS(ec2Client);
            }
        }

    }

    @GetMapping("/get")
    public Ec2Instance get(@RequestParam(name = "region", required = false) String region) {

        List<Ec2Instance> instances = awsInstanceService.getInstanceByRegion(region);

        for(Ec2Instance instance : instances){
            System.out.println("id: " + instance.getInstanceId());
        }

        return null;
    }

    @GetMapping("/amis")
    public void amis(@RequestParam(name = "region", required = false) String region,
                     @RequestParam(name = "keyName", required = false) String keyName,
                     @RequestParam(name = "value", required = false) String value) {

        Ec2Client ec2Client = Ec2Client.builder().region(Region.of(region)).build();
        awsInstanceService.searchAMI(ec2Client, keyName, value);

    }

    @GetMapping("/test")
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        List<awsClient> clients = (ArrayList<awsClient>)context.getBean("awsClients");

        for(awsClient client : clients) {
            boolean done = false;
            String nextToken = null;
            ArrayList<Ec2Instance> awsInstanceDTOS = new ArrayList<Ec2Instance>();
            try{
                do{
                    DescribeInstancesRequest request = DescribeInstancesRequest
                            .builder().maxResults(10).nextToken(nextToken).build();
                    DescribeInstancesResponse response = client.getEc2Clients().get(0).describeInstances(request);

                    for(Reservation reservation : response.reservations()){

                        for(software.amazon.awssdk.services.ec2.model.Instance instance : reservation.instances()){
                            awsInstanceDTOS.add(Ec2Instance.builder().
                                    amiLaunchIndex(instance.amiLaunchIndex()).
                                    imageId(instance.imageId()).
                                    instanceId(instance.instanceId()).
                                    instanceType(instance.instanceTypeAsString()).
                                    kernelId(instance.kernelId()).
                                    keyName(instance.keyName()).
                                    launchTime(instance.launchTime()).
                                    placement(Placement.builder().availabilityZone(instance.placement().availabilityZone())
                                            .affinity(instance.placement().affinity())
                                            .groupName(instance.placement().groupName())
                                            .partitionNumber(instance.placement().partitionNumber())
                                            .hostId(instance.placement().hostId())
                                            .tenancy(instance.placement().tenancyAsString())
                                            .spreadDomain(instance.placement().spreadDomain())
                                            .hostResourceGroupArn(instance.placement().hostResourceGroupArn()).build()).
                                    platform(instance.platformAsString()).
                                    privateDnsName(instance.privateDnsName()).
                                    privateIpAddress(instance.privateIpAddress()).
//                                  productCodes(ProductCode.builder().productCodeId(instance.productCodes().)).
                                    publicDnsName(instance.publicDnsName()).
                                    ramdiskId(instance.ramdiskId()).
//                                 stateReason(StateReason.builder().code(instance.stateReason().code()).message(instance.stateReason().message()).build()).
                                    state(InstanceState.builder().code(instance.state().code()).name(instance.state().nameAsString()).build()).
                                    stateTransitionReason(instance.stateTransitionReason()).
                                    subnetId(instance.subnetId()).
                                    vpcId(instance.vpcId()).
                                    architecture(instance.architectureAsString()).
//                                   blockDeviceMappings(instance.blockDeviceMappings()).
                                    clientToken(instance.clientToken()).
                                    ebsOptimized(instance.ebsOptimized()).
                                    enaSupport(instance.enaSupport()).
                                    hypervisor(instance.hypervisorAsString()).
//                                  iamInstanceProfile(IamInstanceProfile.builder().arn(instance.iamInstanceProfile().getValueForField("Arn",String.class).toString()).id(instance.iamInstanceProfile().getValueForField("id",String.class).toString()).build()).
                                    instanceLifecycle(instance.instanceLifecycleAsString()).
//                                  elasticGpuAssociation(instance.elasticGpuAssociations()).
//                                  elasticInferenceAcceleratorAssociations(instance.elasticInferenceAcceleratorAssociations()).
//                                  networkInterfaces(instance.networkInterfaces()).
                                    outpostArn(instance.outpostArn()).
                                    rootDeviceName(instance.rootDeviceName()).
                                    rootDeviceType(instance.rootDeviceTypeAsString()).
//                                  securityGroups(instance.securityGroups()).
                                    sourceDestCheck(instance.sourceDestCheck()).
                                    spotInstanceRequestId(instance.spotInstanceRequestId()).
                                    sriovNetSupport(instance.sriovNetSupport()).
//                                  tags(instance.tags()).
                                    virtualizationType(instance.virtualizationTypeAsString()).
                                    cpuOptions(CpuOptions.builder().coreCount(instance.cpuOptions().coreCount()).threadsPerCore(instance.cpuOptions().threadsPerCore()).build()).
//                                  capacityReservationSpecification(CapacityReservationSpecificationResponse.builder()
//                                        .capacityReservationPreference(instance.capacityReservationSpecification().capacityReservationPreferenceAsString())
//                                        .capacityReservationTarget(CapacityReservationTargetResponse.builder()
//                                                .capacityReservationId(instance.capacityReservationSpecification().capacityReservationTarget().capacityReservationId())
//                                                .capacityReservationResourceGroupArn(instance.capacityReservationSpecification().capacityReservationTarget().capacityReservationResourceGroupArn()).build())
//                                        .build()).
                                    hibernationOptions(HibernationOptions.builder().configured(instance.hibernationOptions().configured()).build()).
//                                  licenses(instance.licenses()).
                                    metadataOptions(InstanceMetadataOptionsResponse.builder()
                                            .state(instance.metadataOptions().stateAsString())
                                            .httpTokens(instance.metadataOptions().httpTokensAsString())
                                            .httpPutResponseHopLimit(instance.metadataOptions().httpPutResponseHopLimit())
                                            .httpEndpoint(instance.metadataOptions().httpEndpointAsString())
                                            .httpProtocolIpv6(instance.metadataOptions().httpProtocolIpv6AsString())
                                            .instanceMetadataTags(instance.metadataOptions().instanceMetadataTagsAsString()).build()).
                                    enclaveOptions(EnclaveOptions.builder().enabled(instance.enclaveOptions().enabled()).build()).
                                    bootMode(instance.bootModeAsString()).build());


                            System.out.println("Instance Id is " + instance.instanceId());
                            System.out.println("state as string: " + instance.state().nameAsString());
                            System.out.println("state: " + instance.state());
                            System.out.println("state name: " + instance.state().name());
                            System.out.println("--------------------------------------");
                        }
                    }
                } while(nextToken != null);
            }catch (Ec2Exception e){
                System.err.println(e.awsErrorDetails().errorMessage());
                System.exit(1);
            }
        }


        List<vSphereClient> vclients = (ArrayList<vSphereClient>)context.getBean("vSphereClients");
        for(vSphereClient client : vclients) {

            List<VMTypes.Summary> vmList = client.getVmClient().list(new VMTypes.FilterSpec());
            for (VMTypes.Summary vmSummary : vmList) {
                System.out.println(vmSummary);
            }
        }
    }
}