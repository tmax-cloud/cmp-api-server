package com.tmax.cmp;

import com.tmax.cmp.configuration.ClientConfig;
import com.tmax.cmp.entity.common.account.AwsCredentials;
import com.tmax.cmp.entity.common.account.VsphereCredentials;
import com.tmax.cmp.entity.common.client.awsClient;
import com.tmax.cmp.entity.common.client.vSphereClient;
import com.tmax.cmp.svc.common.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class CmpApplication {

	@Autowired
	private CredentialService credentialService;

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);

	public static void main(String[] args) {
		SpringApplication.run(CmpApplication.class, args);
	}

	@PostConstruct
	public void init(){
		setAwsClients();
		setVsphereClients();
	}

	public void setAwsClients(){
		List<awsClient> clientList = (ArrayList<awsClient>)context.getBean("awsClients");
		awsClient client;

		for(AwsCredentials awsCredential : credentialService.getAwsCredentials()){
			client = new awsClient(awsCredential.getAccessKey(),awsCredential.getSecretKey());
			clientList.add(client);
		}
	}

	public void setVsphereClients(){
		List<vSphereClient> clientList = (ArrayList<vSphereClient>)context.getBean("vSphereClients");
		vSphereClient client;

		for(VsphereCredentials vsphereCredentials : credentialService.getVsphereCredentials()){
			client = new vSphereClient(vsphereCredentials.getServer(), vsphereCredentials.getUsername(), vsphereCredentials.getPassword());
			clientList.add(client);
		}
	}

}
