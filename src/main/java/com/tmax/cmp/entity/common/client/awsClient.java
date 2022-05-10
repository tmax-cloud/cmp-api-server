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


    @Getter
    private String region;
    @Getter
    private Ec2Client ec2Client;
    @Getter
    private CostExplorerClient costClient;

    public awsClient(String accessKey, String secretKey, String region) {
        AwsBasicCredentials awsCredential = AwsBasicCredentials.create(accessKey, secretKey);
        this.region = region;
        ec2Client = Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCredential))
                .region(Region.of(region))
                .build();
        costClient = CostExplorerClient.builder()
                .region(Region.of(region))
                .build();
    }
}
