package com.tmax.cmp.component;

import com.tmax.cmp.configuration.ClientConfig;
import com.tmax.cmp.entity.common.client.awsClient;
import com.tmax.cmp.svc.aws.AWSInstanceService;
import com.tmax.cmp.svc.vsphere.VsphereVMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;

import java.util.ArrayList;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private AWSInstanceService awsInstanceService;

    @Autowired
    private VsphereVMService vsphereVMService;

    @Scheduled(fixedDelay = 10 * 60 * 1000)
    public void awsSyncScheduler() throws InterruptedException {
        System.out.println("Current Thread: " + Thread.currentThread().getName());

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        List<awsClient> clients = (ArrayList<awsClient>)context.getBean("awsClients");

        for(awsClient client : clients) {
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

        //client 미리 region 만들어져있음... 가져다 쓰기만 하자. vsphere 랑은 메서드 분리
        //clients 를 for문
        //region별로  for문
//        for(Region region : Region.regions()){
//            try {
//                int instanceCount = awsInstanceService.getAllInstanceFromAWS(region.toString()).size();
//                System.out.println("sync completed | region: " + region.toString());
//                System.out.println("instance count: " + instanceCount);
//            } catch (SdkClientException | Ec2Exception e){
//                System.out.println("sync failed | region : " + region.toString());
//                System.out.println("cannot connect to region");
//            } catch (Exception e) {
//                System.out.println("error occured while sync instance | region: " + region.toString());
//                e.printStackTrace();
//            }
//            System.out.println();
//        }

    }

    @Scheduled(fixedDelay = 30000)
    public void scheduleFixedRateTask2() throws InterruptedException {
        System.out.println("Current Thread: " + Thread.currentThread().getName());
        Thread.sleep(5000);
    }
}
