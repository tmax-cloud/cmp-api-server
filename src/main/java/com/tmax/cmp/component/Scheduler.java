package com.tmax.cmp.component;

import com.tmax.cmp.svc.aws.AWSInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    @Autowired
    private AWSInstanceService awsInstanceService;

    @Scheduled(fixedDelay = 30000)
    public void scheduleFixedRateTask() throws InterruptedException {
        System.out.println("Current Thread: " + Thread.currentThread().getName());
        awsInstanceService.saveInstances("eu-west-2");
    }

    @Scheduled(fixedDelay = 30000)
    public void scheduleFixedRateTask2() throws InterruptedException {
        System.out.println("Current Thread: " + Thread.currentThread().getName());
        Thread.sleep(5000);
    }
}
