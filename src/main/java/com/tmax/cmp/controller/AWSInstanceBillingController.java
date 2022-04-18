package com.tmax.cmp.controller;

import com.tmax.cmp.svc.AWSInstanceBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/billing")
public class AWSInstanceBillingController {

    @Autowired
    AWSInstanceBillingService awsInstanceBillingService;

    @GetMapping("/annual")
    public void getAnnualCost(String startDate, String endDate){

        awsInstanceBillingService.getCostOfInstance(startDate, endDate);
    }

}
