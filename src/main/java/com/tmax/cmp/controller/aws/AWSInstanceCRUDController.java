package com.tmax.cmp.controller.aws;

import com.tmax.cmp.entity.aws.ec2.Ec2Instance;
import com.tmax.cmp.svc.aws.AWSInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/aws/instance")

public class AWSInstanceCRUDController {

    @Autowired
    private AWSInstanceService awsInstanceService;

    @GetMapping("/sync")
    public void syncInstance(@RequestParam(name = "region", required = false) String region){

        ArrayList<Ec2Instance> instanceList = awsInstanceService.getAllInstanceFromAWS(region);

        for(Ec2Instance ec2Instance : instanceList){
            System.out.println("save instance: " + ec2Instance.getInstanceId());
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

}