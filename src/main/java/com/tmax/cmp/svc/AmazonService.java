package com.tmax.cmp.svc;

import com.amazonaws.regions.Regions;
import com.tmax.cmp.dto.AmazonDTO;
import org.springframework.stereotype.Service;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmazonService {
    public AmazonDTO getInstance(final String query, final String region) {
        List<Instance> instances = describeInstances(region);

        for (Instance instance: instances) {
            if (query.equals(instance.getInstanceId())){
                return AmazonDTO.builder().
                        instanceId(instance.getInstanceId()).
                        imageId(instance.getImageId()).
                        keyName(instance.getKeyName()).
                        subnetId(instance.getSubnetId()).
                        vpcId(instance.getVpcId()).
                        privateIpAddress(instance.getPrivateIpAddress()).
                        architecture(instance.getArchitecture()).
                        rootDeviceType(instance.getRootDeviceType()).
                        rootDeviceName(instance.getRootDeviceName()).
                        virtualizationType(instance.getVirtualizationType()).
                        hypervisor(instance.getHypervisor()).build();
            }
        }
        return AmazonDTO.builder().instanceId("test123").imageId("asdf").build();
    }

    public List<AmazonDTO> getAllInstances(final String region) {
        //AmazonService amazonService = new AmazonService();
        //List<Instance> instances = new ArrayList<>();

        List<AmazonDTO> amazonDTOs = new ArrayList<>();
        List<Instance> instances = describeInstances(region);

        for (Instance instance: instances) {
            amazonDTOs.add(AmazonDTO.builder().
                    instanceId(instance.getInstanceId()).
                    imageId(instance.getImageId()).
                    keyName(instance.getKeyName()).
                    subnetId(instance.getSubnetId()).
                    vpcId(instance.getVpcId()).
                    privateIpAddress(instance.getPrivateIpAddress()).
                    architecture(instance.getArchitecture()).
                    rootDeviceType(instance.getRootDeviceType()).
                    rootDeviceName(instance.getRootDeviceName()).
                    virtualizationType(instance.getVirtualizationType()).
                    hypervisor(instance.getHypervisor()).build());
        }
        return amazonDTOs;
        /*
        return Arrays.asList(
                Amazon.builder().instanceId("test123").imageId("asdf").build(),
                Amazon.builder().instanceId("test456").imageId("gexz").build()
        );
        */

    }

    public List<Instance> describeInstances(String region)
    {
        //final AmazonEC2 ec2 = AmazonEC2ClientBuilder.defaultClient();
        AmazonEC2 ec2;
        if (region == null) {
            ec2 = AmazonEC2ClientBuilder.standard()
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .build();
        } else {
            ec2 = AmazonEC2ClientBuilder.standard()
                    .withRegion(Regions.valueOf(region))
                    .build();
        }
        boolean done = false;

        DescribeInstancesRequest request = new DescribeInstancesRequest();
        List<Instance> instances = new ArrayList<>();

        while(!done) {
            DescribeInstancesResult response = ec2.describeInstances(request);

            for(Reservation reservation : response.getReservations()) {
                for(Instance instance : reservation.getInstances()) {
                    instances.add(instance);
                }
            }

            request.setNextToken(response.getNextToken());

            if(response.getNextToken() == null) {
                done = true;
            }
        }
        return instances;
    }
}
