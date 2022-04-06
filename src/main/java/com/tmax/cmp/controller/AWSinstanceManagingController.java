package com.tmax.cmp.controller;

import com.tmax.cmp.svc.AWSInstanceMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

@RestController
@RequestMapping("/api/v1/instancemanaging")
public class AWSinstanceManagingController {

    @Autowired
    AWSInstanceMonitoringService awsInstanceMonitoringService;

    @GetMapping("/monitor")
    public void monitorInstance(@RequestParam(name = "instanceId") String instanceId){

        Ec2Client ec2Client = Ec2Client.builder().region(Region.EU_WEST_2).build();
        awsInstanceMonitoringService.monitorInstance(ec2Client, instanceId);

    }

}
