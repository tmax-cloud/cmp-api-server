package com.tmax.cmp.configuration;

import com.tmax.cmp.entity.common.client.awsClient;
import com.tmax.cmp.entity.common.client.vSphereClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ClientConfig {

    private static List<awsClient> clientList = new ArrayList<>();

    @Bean
    public List<awsClient> awsClients(){ return clientList; }

    @Bean
    public List<vSphereClient> vSphereClients() {
        List<vSphereClient> clientList = new ArrayList<vSphereClient>();
        vSphereClient client;


        client = new vSphereClient("192.168.9.231","administrator@vsphere.local","Tmax@2323");
        clientList.add(client);


        return clientList;
    }
}
