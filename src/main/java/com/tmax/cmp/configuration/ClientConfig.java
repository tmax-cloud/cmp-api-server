package com.tmax.cmp.configuration;

import com.tmax.cmp.entity.common.client.awsClient;
import com.tmax.cmp.entity.common.client.vSphereClient;
import com.tmax.cmp.repository.CredentialRepository;
import com.tmax.cmp.svc.common.CredentialService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableJpaRepositories(basePackages = "com.tmax.cmp.repository", entityManagerFactoryRef="emf")
public class ClientConfig {
//

    CredentialService credentialService;

    CredentialRepository credentialRepository;

    @Bean
    public List<awsClient> awsClients(){

        List<awsClient> clientList = new ArrayList<awsClient>();
//        List<AwsCredentials> awsCredentialsList = credentialService.getAwsCredentials();

//        for(AwsCredentials awsCredentials : awsCredentialsList){
//            awsClient client;
//            client = new awsClient(awsCredentials.getAccessKey(),awsCredentials.getSecretKey());
//            clientList.add(client);
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
