package com.tmax.cmp.svc;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.costexplorer.CostExplorerClient;
import software.amazon.awssdk.services.costexplorer.model.*;

@Service
public class AWSInstanceBillingService {

    public void getCostOfInstance(String startDate, String endDate){

        CostExplorerClient costClient = CostExplorerClient.builder().region(Region.US_EAST_1).build();
        GroupDefinition groupDefinition = GroupDefinition.builder().type("DIMENSION").key("AZ").build();
        DateInterval dateInterval = DateInterval.builder().start(startDate).end(endDate).build();
        String metrics = "BlendedCost";
        GetCostAndUsageRequest request = GetCostAndUsageRequest
                .builder().timePeriod(dateInterval).granularity("MONTHLY").metrics(metrics)
                .groupBy(groupDefinition).build();

        GetCostAndUsageResponse response = costClient.getCostAndUsage(request);

        Object[] costResponse = response.resultsByTime().toArray();

        int time = 1;
        for(Object obj : costResponse){

            ResultByTime resultByTime = (ResultByTime)obj;

            System.out.println("=== Time" + time + ": " + resultByTime.timePeriod().start() + " ~ " + resultByTime.timePeriod().end() + " ===");
            String cost;
            String unit;
            String region;

            for(Group group : resultByTime.groups()){

//                System.out.println(group.toString());

                cost = group.metrics().get(metrics).amount();
                unit = group.metrics().get(metrics).unit();
                region = group.keys().toString();
                System.out.println("Region: " + region.substring(1,region.length()-1).toUpperCase() + " | Cost: " + cost + " " + unit);
            }
            System.out.println();

            time++;
        }

    }
}
