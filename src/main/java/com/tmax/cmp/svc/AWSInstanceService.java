package com.tmax.cmp.svc;

import com.tmax.cmp.dto.AWSInstanceDTO;
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

    @Autowired
    AmazonService amazonService;

    public void saveInstances(String region) {
        Region defRegion;

        if (region == null) {
           defRegion = Region.EU_WEST_2;
        } else {
           defRegion = Region.of(region);
        }

        Ec2Client ec2Client = Ec2Client.builder().region(defRegion).build();
        ArrayList<AWSInstanceDTO> instances = describeAWSInstances(ec2Client);
        awsInstanceRepository.saveAll(instances);

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

    public ArrayList<AWSInstanceDTO> describeAWSInstances(Ec2Client ec2Client){

        boolean done = false;
        String nextToken = null;
        ArrayList<AWSInstanceDTO> awsInstanceDTOS = new ArrayList<AWSInstanceDTO>();
        try{
            do{
                DescribeInstancesRequest request = DescribeInstancesRequest
                        .builder().maxResults(10).nextToken(nextToken).build();
                DescribeInstancesResponse response = ec2Client.describeInstances(request);

                for(Reservation reservation : response.reservations()){

                    for(Instance instance : reservation.instances()){
                        awsInstanceDTOS.add(AWSInstanceDTO.builder().
                                instanceId(instance.instanceId()).
                                imageId(instance.imageId()).
                                keyName(instance.keyName()).
                                subnetId(instance.subnetId()).
                                vpcId(instance.vpcId()).
                                privateIpAddress(instance.privateIpAddress()).
                                architecture(instance.architectureAsString()).
                                rootDeviceType(instance.rootDeviceTypeAsString()).
                                rootDeviceName(instance.rootDeviceName()).
                                stateName(instance.state().nameAsString()).
                                stateReason(instance.stateReason()).
                                virtualizationType(instance.virtualizationTypeAsString()).
                                hypervisor(instance.hypervisorAsString())
                                .build());

                        System.out.println("Instance Id is " + instance.instanceId());
                        System.out.println("Image Id is " + instance.imageId());
                        System.out.println("Instance type is " + instance.instanceType());
                        System.out.println("Instance stateName is " + instance.state().nameAsString());
                        System.out.println("Instance stateReason is " + instance.stateReason());
                        System.out.println("monitoring information is " + instance.monitoring().state());
                    }
                }
            } while(nextToken != null);
        }catch (Ec2Exception e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }

        return awsInstanceDTOS;
    }
}
