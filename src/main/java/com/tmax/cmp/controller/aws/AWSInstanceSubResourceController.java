package com.tmax.cmp.controller.aws;

import com.tmax.cmp.svc.aws.AWSInstanceSubResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

@RestController
@RequestMapping("/api/v1/aws/instance/subresource")
public class AWSInstanceSubResourceController {

    @Autowired
    private AWSInstanceSubResourceService awsInstanceSubResourceService;

    @GetMapping("/keypairs")
    public void getKeyPairs(@RequestParam(name = "region") String region){

        Ec2Client ec2Client = Ec2Client.builder().region(Region.of(region)).build();

        awsInstanceSubResourceService.getListOfKeyPairs(ec2Client);
    }

    @GetMapping({"/vpcs"})
    public void getVpcs(@RequestParam(name = "region") String region){

        Ec2Client ec2Client = Ec2Client.builder().region(Region.of(region)).build();

        awsInstanceSubResourceService.getListOfVPC(ec2Client);
    }

    @GetMapping({"/subnet"})
    public void getSubnets(@RequestParam(name = "region") String region,
                           @RequestParam(name = "vpcId") String vpcId){

        Ec2Client ec2Client = Ec2Client.builder().region(Region.of(region)).build();

        awsInstanceSubResourceService.getListOfSubnet(ec2Client, vpcId);
    }
}
