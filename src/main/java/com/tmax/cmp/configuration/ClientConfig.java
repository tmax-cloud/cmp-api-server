package com.tmax.cmp.configuration;

import com.tmax.cmp.entity.aws.ec2.Ec2Instance;
import com.tmax.cmp.entity.common.client.Client;
import com.tmax.cmp.entity.common.client.awsClient;
import com.tmax.cmp.entity.common.client.vSphereClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ClientConfig {

    @Bean
    public List<awsClient> awsClients(){
        List<awsClient> clientList = new ArrayList<awsClient>();
        awsClient client;

//        for( ){                                       // Hibernate 기반 credential querying
        client = new awsClient("AKIAQDF4RKX2TA6RSGWV","SqsKfTVbrZ5m5SZPqnu5ZWpFj4lRybFjTR93N3bu", "us-east-2");
        clientList.add(client);


        client = new awsClient("AKIAQDF4RKX2TA6RSGWV","SqsKfTVbrZ5m5SZPqnu5ZWpFj4lRybFjTR93N3bu", "ap-northeast-1");
        clientList.add(client);
//        }

        return clientList;
    }

    @Bean
    public List<vSphereClient> vSphereClients() {
        List<vSphereClient> clientList = new ArrayList<vSphereClient>();
        vSphereClient client;

        //for
        client = new vSphereClient("192.168.9.231","administrator@vsphere.local","Tmax@2323");
        clientList.add(client);
        //

        return clientList;
    }
}