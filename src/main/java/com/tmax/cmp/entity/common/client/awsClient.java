package com.tmax.cmp.entity.common.client;

import lombok.Getter;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.costexplorer.CostExplorerClient;
import software.amazon.awssdk.services.ec2.Ec2Client;

import java.util.ArrayList;
import java.util.List;

public class awsClient implements Client{


    private AwsBasicCredentials awsCreds;
    @Getter
    private String region;
    @Getter
    private Ec2Client ec2Client;
    @Getter
    private CostExplorerClient costClient;
    @Getter
    private List<Ec2Client> ec2ClientList;

    public awsClient(String accessKey, String secretKey, String region){
        this.region = region;
        awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
        ec2Client = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(Region.of(region))
                .build();
        costClient = CostExplorerClient.builder()
                .region(Region.of(region))
                .build();



//        ec2ClientList = new ArrayList<Ec2Client>();
//        for (Region region : Region.regions()) {
//            ec2Client = Ec2Client.builder()
//                    .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
//                    .region(region)
//                    .build();
//            ec2ClientList.add(ec2Client);
//        }
    }
}
