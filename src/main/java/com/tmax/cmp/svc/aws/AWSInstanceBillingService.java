package com.tmax.cmp.svc.aws;

import com.tmax.cmp.configuration.ClientConfig;
import com.tmax.cmp.entity.common.client.awsClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.costandusagereport.CostAndUsageReportClient;
import software.amazon.awssdk.services.costandusagereport.model.DescribeReportDefinitionsRequest;
import software.amazon.awssdk.services.costandusagereport.model.DescribeReportDefinitionsResponse;
import software.amazon.awssdk.services.costexplorer.CostExplorerClient;
import software.amazon.awssdk.services.costexplorer.model.*;

import java.util.List;

@Service
public class AWSInstanceBillingService {

    public void getCostOfInstance(String startDate, String endDate, String metrics, String granularity,
                                  String group, String dimensionKey, String dimensionValue){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        List<awsClient> awsClientList = (List<awsClient>)context.getBean("awsClients");

        for(awsClient awsClient : awsClientList){
            for(CostExplorerClient costExplorerClient : awsClient.getCostExplorerClients()){

                GroupDefinition groupDefinition = GroupDefinition.builder().type("DIMENSION").key(group).build();

                DateInterval dateInterval = DateInterval.builder().start(startDate).end(endDate).build();

                Expression exp = null;


                if(dimensionKey != null || dimensionValue != null){
                    exp = Expression.builder()
                        .dimensions(DimensionValues.builder()
                                .key(dimensionKey).values(dimensionValue)
                                .build())
                        .build();
                }

                GetCostAndUsageRequest request = GetCostAndUsageRequest
                        .builder().timePeriod(dateInterval).granularity(granularity).metrics(metrics)
                        .groupBy(groupDefinition)
                        .filter(exp)
                        .build();

                System.out.println("==============costAndUsage=================");
                GetCostAndUsageResponse response = costExplorerClient.getCostAndUsage(request);

                Object[] costResponse = response.resultsByTime().toArray();


                int time = 1;
                for(Object obj : costResponse){

                    ResultByTime resultByTime = (ResultByTime)obj;

                    System.out.println("=== Time" + time + ": " + resultByTime.timePeriod().start() + " ~ " + resultByTime.timePeriod().end() + " ===");
                    String cost;
                    String unit;
                    String groupResponse;

                    for(Group groupResult : resultByTime.groups()){

                        cost = groupResult.metrics().get(metrics).amount();
                        unit = groupResult.metrics().get(metrics).unit();
                        groupResponse = groupResult.keys().toString();
                        System.out.println("Group: " + groupResponse.substring(1,groupResponse.length()-1).toUpperCase() + " | Cost: " + cost + " " + unit);
                    }
                    System.out.println();

                    time++;
                }


            }
        }

    }

    public void getCostAndUsageReport(){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        List<awsClient> awsClientList = (List<awsClient>)context.getBean("awsClients");

        for(awsClient awsClient : awsClientList) {
            for (CostAndUsageReportClient costAndUsageReportClient : awsClient.getCostAndUsageReportClients()) {

                DescribeReportDefinitionsRequest request = DescribeReportDefinitionsRequest
                        .builder().maxResults(5).build();

                DescribeReportDefinitionsResponse response = costAndUsageReportClient.describeReportDefinitions(request);
                System.out.println("=========Report=========");
                System.out.println(response.toString());
            }
        }

    }

    public void getDimensionValues(String dimensionContext, String dimensionKey, String startDate, String endDate) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        List<awsClient> awsClientList = (List<awsClient>) context.getBean("awsClients");

        DateInterval dateInterval = DateInterval.builder().start(startDate).end(endDate).build();

        for (awsClient awsClient : awsClientList) {
            for (CostExplorerClient costExplorerClient : awsClient.getCostExplorerClients()) {


                GetDimensionValuesRequest request1 = GetDimensionValuesRequest
                        .builder().context(dimensionContext).dimension(Dimension.fromValue(dimensionKey)).timePeriod(dateInterval).build();


                GetDimensionValuesResponse response1 = costExplorerClient.getDimensionValues(request1);

                System.out.println("============= DimensionValuesWith Attributes =============");
                for (DimensionValuesWithAttributes dimensionValuesWithAttributes : response1.dimensionValues()) {
                    System.out.println(dimensionValuesWithAttributes.value() + " : " + dimensionValuesWithAttributes.attributes());
                }
            }

        }
    }

}
