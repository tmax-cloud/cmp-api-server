package com.tmax.cmp.svc;

import com.amazonaws.regions.Regions;
import com.tmax.cmp.dto.AWSInstanceDTO;
import com.tmax.cmp.repository.AWSInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AWSInstanceService {
    @Autowired
    private AWSInstanceRepository awsInstanceRepository;

    public void saveInstances(String region)
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
        //AWSInstanceDTO awsInstance = new AWSInstanceDTO();


        while(!done) {
            DescribeInstancesResult response = ec2.describeInstances(request);
            for(Reservation reservation : response.getReservations()) {
                for(Instance instance : reservation.getInstances()) {
                    //.add(instance);
                    AWSInstanceDTO awsInstance = AWSInstanceDTO.builder().
                            instanceId(instance.getInstanceId()).
                            imageId(instance.getImageId()).
                            vpcId(instance.getVpcId()).build();

                    System.out.println("TEST:"+ awsInstance.getInstanceId());
                    awsInstanceRepository.save(awsInstance);
                }
            }

            request.setNextToken(response.getNextToken());

            if(response.getNextToken() == null) {
                done = true;
            }
        }
    }
}
