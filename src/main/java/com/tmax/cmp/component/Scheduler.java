package com.tmax.cmp.component;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tmax.cmp.svc.AWSInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class Scheduler {
    @Autowired
    private AWSInstanceService awsService;

    @Scheduled(fixedDelay = 30000)
    public void scheduleFixedRateTask() {
        awsService.saveInstances("AP_NORTHEAST_2");
    }
}
