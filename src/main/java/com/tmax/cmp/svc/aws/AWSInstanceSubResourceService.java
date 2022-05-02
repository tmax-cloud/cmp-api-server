package com.tmax.cmp.svc.aws;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

@Service
public class AWSInstanceSubResourceService {

    public void getListOfKeyPairs(Ec2Client ec2Client){

        try{

            DescribeKeyPairsResponse response = ec2Client.describeKeyPairs();

            for(KeyPairInfo keyPair : response.keyPairs()) {
                System.out.println("keyPair Name: " + keyPair.keyName());
                System.out.println("keyPair fingerPrint: " + keyPair.keyFingerprint());
                System.out.println();
            }

        }catch (Ec2Exception e){
            System.out.println("error occured......");
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }

    public void getListOfVPC(Ec2Client ec2Client){

        try{

            DescribeVpcsResponse response = ec2Client.describeVpcs();

            for(Vpc vpc : response.vpcs()){
                System.out.println("VPC ID: " + vpc.vpcId());
                System.out.println("VPC cidrBlock: " + vpc.cidrBlock());
                System.out.println();
            }

        } catch (Ec2Exception e){
            System.out.println("error occured......");
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }

    public void getListOfSubnet(Ec2Client ec2Client, String vpcId){

        try{

            DescribeSubnetsRequest request = DescribeSubnetsRequest.builder()
                    .filters(Filter.builder().name("vpc-id").values(vpcId).build())
                    .build();

            DescribeSubnetsResponse response = ec2Client.describeSubnets(request);
            Object[] subnetList = response.subnets().toArray();

            for(Object obj : subnetList){
                Subnet subnet = (Subnet)obj;
                System.out.println("Subnet Id: " + subnet.subnetId());
                System.out.println("Subnet cidr: " + subnet.cidrBlock());
            }
        }catch (Ec2Exception e){
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
