package com.tmax.cmp.component;

import com.tmax.cmp.configuration.ClientConfig;
import com.tmax.cmp.entity.common.client.awsClient;
import com.tmax.cmp.svc.aws.AWSInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;

import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private AWSInstanceService awsInstanceService;

    @Scheduled(fixedDelay = 10 * 60 * 1000)
    public void awsSyncScheduler() throws InterruptedException {
        System.out.println("Current Thread: " + Thread.currentThread().getName());

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        List<awsClient> awsClientList = (List<awsClient>)context.getBean("awsClients");

        for(awsClient client : awsClientList) {
            for(Ec2Client ec2Client : client.getEc2Clients()){
                try {
                    int instanceCount = awsInstanceService.getAllInstanceFromAWS(ec2Client).size();
                    System.out.println("sync completed | region: " + ec2Client.toString());
                    System.out.println("instance count: " + instanceCount);
                } catch (SdkClientException | Ec2Exception e){
                    System.out.println("sync failed | region : " + ec2Client.toString());
                    System.out.println("cannot connect to region");
                } catch (Exception e) {
                    System.out.println("error occured while sync instance | region: " + ec2Client.toString());
                    e.printStackTrace();
                }
                System.out.println();
            }
        }

    }

    @Scheduled(fixedDelay = 30000)
    public void scheduleFixedRateTask2() throws InterruptedException {
        System.out.println("Current Thread: " + Thread.currentThread().getName());
        Thread.sleep(5000);
    }
//
//    @Scheduled(fixedDelay = 10 * 60 * 1000)
//    public void setAwsCredentials() throws InterruptedException {
//        System.out.println("set aws credentials");
//        credentialService.addAwsClientBeans();
//    }
}
