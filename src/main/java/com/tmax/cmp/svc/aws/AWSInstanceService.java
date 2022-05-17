package com.tmax.cmp.svc.aws;

import com.tmax.cmp.entity.aws.ec2.CpuOptions;
import com.tmax.cmp.entity.aws.ec2.EnclaveOptions;
import com.tmax.cmp.entity.aws.ec2.HibernationOptions;
import com.tmax.cmp.entity.aws.ec2.InstanceMetadataOptionsResponse;
import com.tmax.cmp.entity.aws.ec2.InstanceState;
import com.tmax.cmp.entity.aws.ec2.Placement;
import com.tmax.cmp.entity.aws.ec2.*;
import com.tmax.cmp.repository.AWSInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class AWSInstanceService {
    @Autowired
    private AWSInstanceRepository awsInstanceRepository;

    public void saveInstances(String region) throws Exception{
        Region defRegion;

        if (region == null) {
           defRegion = Region.EU_WEST_2;
        } else {
           defRegion = Region.of(region);
        }

        Ec2Client ec2Client = Ec2Client.builder().region(defRegion).build();
        ArrayList<Ec2Instance> instances = getAllInstanceFromAWS(defRegion.id());
        awsInstanceRepository.saveAll(instances);

    }

    public List<Ec2Instance> getInstanceByRegion(final String region) {

        List<Ec2Instance> ec2Instance = awsInstanceRepository.findByRegion(region);

        return ec2Instance;
    }

    public void searchAMI(Ec2Client ec2Client, String keyName, String value){

        DescribeImagesRequest request = DescribeImagesRequest.builder()
                .filters(Filter.builder().name(keyName).values(value).build())
                .build();

        DescribeImagesResponse response = ec2Client.describeImages(request);


        Object[] imageList = response.images().toArray();

        for(Object obj : imageList){
            Image image = (Image)obj;
            System.out.println("Image Id:" + image.imageId());
        }

        System.out.println("total image e/a : " + response.images().size());
    }

    public ArrayList<Ec2Instance> getAllInstanceFromAWS(String region) throws Exception{

        Ec2Client ec2Client = Ec2Client.builder().region(Region.of(region)).build();

        boolean done = false;
        String nextToken = null;
        ArrayList<Ec2Instance> awsInstanceDTOS = new ArrayList<Ec2Instance>();
//        try{
            do{
                DescribeInstancesRequest request = DescribeInstancesRequest
                        .builder().maxResults(10).nextToken(nextToken).build();
                DescribeInstancesResponse response = ec2Client.describeInstances(request);

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
//                                productCodes(ProductCode.builder().productCodeId(instance.productCodes().)).
                                publicDnsName(instance.publicDnsName()).
                                ramdiskId(instance.ramdiskId()).
//                                stateReason(StateReason.builder().code(instance.stateReason().code()).message(instance.stateReason().message()).build()).
                                state(InstanceState.builder().code(instance.state().code()).name(instance.state().nameAsString()).build()).
                                stateTransitionReason(instance.stateTransitionReason()).
                                subnetId(instance.subnetId()).
                                vpcId(instance.vpcId()).
                                architecture(instance.architectureAsString()).
//                                blockDeviceMappings(instance.blockDeviceMappings()).
                                clientToken(instance.clientToken()).
                                ebsOptimized(instance.ebsOptimized()).
                                enaSupport(instance.enaSupport()).
                                hypervisor(instance.hypervisorAsString()).
//                                iamInstanceProfile(IamInstanceProfile.builder().arn(instance.iamInstanceProfile().getValueForField("Arn",String.class).toString()).id(instance.iamInstanceProfile().getValueForField("id",String.class).toString()).build()).
                                instanceLifecycle(instance.instanceLifecycleAsString()).
//                                elasticGpuAssociation(instance.elasticGpuAssociations()).
//                                elasticInferenceAcceleratorAssociations(instance.elasticInferenceAcceleratorAssociations()).
//                                networkInterfaces(instance.networkInterfaces()).
                                outpostArn(instance.outpostArn()).
                                rootDeviceName(instance.rootDeviceName()).
                                rootDeviceType(instance.rootDeviceTypeAsString()).
//                                securityGroups(instance.securityGroups()).
                                sourceDestCheck(instance.sourceDestCheck()).
                                spotInstanceRequestId(instance.spotInstanceRequestId()).
                                sriovNetSupport(instance.sriovNetSupport()).
//                                tags(instance.tags()).
                                virtualizationType(instance.virtualizationTypeAsString()).
                                cpuOptions(CpuOptions.builder().coreCount(instance.cpuOptions().coreCount()).threadsPerCore(instance.cpuOptions().threadsPerCore()).build()).
//                                capacityReservationSpecification(CapacityReservationSpecificationResponse.builder()
//                                        .capacityReservationPreference(instance.capacityReservationSpecification().capacityReservationPreferenceAsString())
//                                        .capacityReservationTarget(CapacityReservationTargetResponse.builder()
//                                                .capacityReservationId(instance.capacityReservationSpecification().capacityReservationTarget().capacityReservationId())
//                                                .capacityReservationResourceGroupArn(instance.capacityReservationSpecification().capacityReservationTarget().capacityReservationResourceGroupArn()).build())
//                                        .build()).
                                hibernationOptions(HibernationOptions.builder().configured(instance.hibernationOptions().configured()).build()).
//                                licenses(instance.licenses()).
                                metadataOptions(InstanceMetadataOptionsResponse.builder()
                                        .state(instance.metadataOptions().stateAsString())
                                        .httpTokens(instance.metadataOptions().httpTokensAsString())
                                        .httpPutResponseHopLimit(instance.metadataOptions().httpPutResponseHopLimit())
                                        .httpEndpoint(instance.metadataOptions().httpEndpointAsString())
                                        .httpProtocolIpv6(instance.metadataOptions().httpProtocolIpv6AsString())
                                        .instanceMetadataTags(instance.metadataOptions().instanceMetadataTagsAsString()).build()).
                                enclaveOptions(EnclaveOptions.builder().enabled(instance.enclaveOptions().enabled()).build()).
                                bootMode(instance.bootModeAsString()).
                                region(region).build());


                        System.out.println("Instance Id is " + instance.toBuilder().toString());
                        System.out.println("state as string: " + instance.state().nameAsString());
                        System.out.println("state: " + instance.state());
                        System.out.println("state name: " + instance.state().name());
//                        System.out.println("Image Id is " + instance.imageId());
//                        System.out.println("Instance type is " + instance.instanceType());
//                        System.out.println("Instance stateName is " + instance.state().nameAsString());
//                        System.out.println("Instance stateReason is " + instance.stateReason());
//                        System.out.println("monitoring information is " + instance.monitoring().state());


//                        System.out.println("total:" + instance.toString());
                    }
                }
            } while(nextToken != null);
//        }catch (Ec2Exception e){
//            System.err.println(e.awsErrorDetails().errorMessage());
//            System.exit(1);
//        }

        awsInstanceRepository.saveAll(awsInstanceDTOS);

        return awsInstanceDTOS;
    }

}
