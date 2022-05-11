package com.tmax.cmp.svc.aws;

import com.tmax.cmp.entity.aws.ec2.Ec2Instance;
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

    public void saveInstances(String region) {
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

    public ArrayList<Ec2Instance> getAllInstanceFromAWS(String region){

        Ec2Client ec2Client = Ec2Client.builder().region(Region.of(region)).build();

        boolean done = false;
        String nextToken = null;
        ArrayList<Ec2Instance> awsInstanceDTOS = new ArrayList<Ec2Instance>();
        try{
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
                                placement(instance.placement()).
                                platform(instance.platformAsString()).
                                privateDnsName(instance.privateDnsName()).
                                privateIpAddress(instance.privateIpAddress()).
//                                productCodes(instance.productCodes()).
                                publicDnsName(instance.publicDnsName()).
                                ramdiskId(instance.ramdiskId()).
                                stateReason(instance.stateReason()).
                                state(instance.state().nameAsString()).
                                stateTransitionReason(instance.stateTransitionReason()).
                                subnetId(instance.subnetId()).
                                vpcId(instance.vpcId()).
                                architecture(instance.architectureAsString()).
//                                blockDeviceMappings(instance.blockDeviceMappings()).
                                clientToken(instance.clientToken()).
                                ebsOptimized(instance.ebsOptimized()).
                                enaSupport(instance.enaSupport()).
                                hypervisor(instance.hypervisorAsString()).
                                iamInstanceProfile(instance.iamInstanceProfile()).
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
                                cpuOptions(instance.cpuOptions()).
                                capacityReservationSpecification(instance.capacityReservationSpecification()).
                                hibernationOptions(instance.hibernationOptions()).
//                                licenses(instance.licenses()).
                                metadataOptions(instance.metadataOptions()).
//                                enclaveOptions(instance.enclaveOptions()).
                                bootMode(instance.bootModeAsString()).
                                region(region).build());


                        System.out.println("Instance Id is " + instance.instanceId());
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
        }catch (Ec2Exception e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }

        awsInstanceRepository.saveAll(awsInstanceDTOS);

        return awsInstanceDTOS;
    }

}
