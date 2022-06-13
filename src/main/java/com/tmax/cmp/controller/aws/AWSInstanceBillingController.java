package com.tmax.cmp.controller.aws;

import com.tmax.cmp.svc.aws.AWSInstanceBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/aws/instance/billing")
public class AWSInstanceBillingController {

    @Autowired
    AWSInstanceBillingService awsInstanceBillingService;

    @GetMapping("/annual")
    public void getMetricAndBillings(String startDate, String endDate, String metrics, String granularity,
                                     String group, String dimensionKey, String dimensionValue){

        awsInstanceBillingService.getCostOfInstance(startDate, endDate, metrics, granularity, group, dimensionKey, dimensionValue);
    }

    @GetMapping("/dimensionValue")
    public void getDimensionValue(String startDate, String endDate, String dimensionContext, String dimensionKey){

        awsInstanceBillingService.getDimensionValues(dimensionContext, dimensionKey, startDate, endDate);
    }

    @GetMapping("/costAndUsageReport")
    public void getCostAndUsageReport(){

        awsInstanceBillingService.getCostAndUsageReport();
    }

}
