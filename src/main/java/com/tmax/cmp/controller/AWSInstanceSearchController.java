package com.tmax.cmp.controller;

import com.tmax.cmp.dto.AWSInstanceDTO;
import com.tmax.cmp.dto.AmazonDTO;
import com.tmax.cmp.repository.AWSInstanceRepository;
import com.tmax.cmp.svc.AWSInstanceService;
import com.tmax.cmp.svc.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/awssearch")
public class AWSInstanceSearchController {
    /*
    @Autowired
    private AWSInstanceRepository awsInstanceRepository;

    @GetMapping("/instance")
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
    */

    @Autowired
    private AWSInstanceService awsService;

    @GetMapping("/instance")
    public void test() {
        awsService.saveInstances("AP_NORTHEAST_2");
    }

}