package com.tmax.cmp.svc;

import com.tmax.cmp.dto.AmazonDTO;
import org.springframework.stereotype.Service;


import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.Reservation;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmazonService {
    public AmazonDTO getInstance(final String query, final String region) {

        Ec2Client ec2Client = Ec2Client.create();

        List<Instance> instances = describeInstances(ec2Client, region);
        
        for (Instance instance: instances) {
            if (query.equals(instance.instanceId())){
                return AmazonDTO.builder().
                        instanceId(instance.instanceId()).
                        imageId(instance.imageId()).
                        keyName(instance.keyName()).
                        subnetId(instance.subnetId()).
                        vpcId(instance.vpcId()).
                        privateIpAddress(instance.privateIpAddress()).
                        architecture(instance.architectureAsString()).
                        rootDeviceType(instance.rootDeviceTypeAsString()).
                        rootDeviceName(instance.rootDeviceName()).
                        virtualizationType(instance.virtualizationTypeAsString()).
                        hypervisor(instance.hypervisorAsString()).build();
            }
        }
        return AmazonDTO.builder().instanceId("test123").imageId("asdf").build();
    }

    public ArrayList<AmazonDTO> getAllInstances(final String region) {

        Ec2Client ec2Client = Ec2Client.create();

        ArrayList<AmazonDTO> amazonDTOs = new ArrayList<>();
        List<Instance> instances = describeInstances(ec2Client, region);

        for (Instance instance: instances) {
            amazonDTOs.add(AmazonDTO.builder().
                    instanceId(instance.instanceId()).
                    imageId(instance.imageId()).
                    keyName(instance.keyName()).
                    subnetId(instance.subnetId()).
                    vpcId(instance.vpcId()).
                    privateIpAddress(instance.privateIpAddress()).
                    architecture(instance.architectureAsString()).
                    rootDeviceType(instance.rootDeviceTypeAsString()).
                    rootDeviceName(instance.rootDeviceName()).
                    virtualizationType(instance.virtualizationTypeAsString()).
                    hypervisor(instance.hypervisorAsString()).build());
        }
        return amazonDTOs;
        /*
        return Arrays.asList(
                Amazon.builder().instanceId("test123").imageId("asdf").build(),
                Amazon.builder().instanceId("test456").imageId("gexz").build()
        );
        */

    }

    public List<Instance> describeInstances(Ec2Client ec2Client, String region)
    {

        boolean done = false;
        String nextToken = null;

        DescribeInstancesRequest request = DescribeInstancesRequest
                .builder().maxResults(6).nextToken(nextToken).build();
        List<Instance> instances = new ArrayList<>();
        DescribeInstancesResponse response = ec2Client.describeInstances(request);

        while(!done) {

            for(Reservation reservation : response.reservations()) {
                for(Instance instance : reservation.instances()) {
                    instances.add(instance);
                }
            }

            nextToken = response.nextToken();

        }
        return instances;
    }
}
