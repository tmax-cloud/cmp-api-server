package com.tmax.cmp.svc;


import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.MonitorInstancesRequest;
import software.amazon.awssdk.services.ec2.model.MonitorInstancesResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.model.UnmonitorInstancesRequest;


@Service
public class AWSInstanceMonitoringService {

    public void monitorMain(Ec2Client ec2Client, String instanceId){

        final String USAGE = "\n" +
                "Usage:\n" +
                "   <instanceId> <monitor>\n\n" +
                "Where:\n" +
                "   instanceId - an instance id value that you can obtain from the AWS Console. \n\n" +
                "   monitor - a monitoring status (true|false)";

        boolean monitor = Boolean.valueOf(instanceId);

        Region region = Region.EU_WEST_2;
        Ec2Client ec2 = Ec2Client.builder()
                .region(region)
                .build();

        if (monitor) {
            monitorInstance(ec2, instanceId);
        } else {
            unmonitorInstance(ec2, instanceId);
        }
        ec2.close();
    }

    // snippet-start:[ec2.java2.monitor_instance.main]
    public static void monitorInstance(Ec2Client ec2, String instanceId) {

        MonitorInstancesRequest request = MonitorInstancesRequest.builder()
                .instanceIds(instanceId).build();

        ec2.monitorInstances(request);
        System.out.printf(
                "Successfully enabled monitoring for instance %s",
                instanceId);
    }
    // snippet-end:[ec2.java2.monitor_instance.main]

    // snippet-start:[ec2.java2.monitor_instance.stop]
    public static void unmonitorInstance(Ec2Client ec2, String instanceId) {
        UnmonitorInstancesRequest request = UnmonitorInstancesRequest.builder()
                .instanceIds(instanceId).build();

        ec2.unmonitorInstances(request);

        System.out.printf(
                "Successfully disabled monitoring for instance %s",
                instanceId);
    }
}
