package com.tmax.cmp.configuration;

import com.tmax.cmp.entity.common.client.awsClient;
import com.tmax.cmp.entity.common.client.vSphereClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ClientConfig {

    private static List<awsClient> awsClientList = new ArrayList<>();
    private static List<vSphereClient> vsphereClientList = new ArrayList<>();

    @Bean
    public List<awsClient> awsClients(){ return awsClientList; }

    @Bean
    public List<vSphereClient> vSphereClients() { return vsphereClientList; }
}
