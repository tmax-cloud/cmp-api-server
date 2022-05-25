package com.tmax.cmp;

import com.tmax.cmp.configuration.ClientConfig;
import com.tmax.cmp.entity.common.account.AwsCredentials;
import com.tmax.cmp.entity.common.client.awsClient;
import com.tmax.cmp.svc.common.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class CmpApplication {
	@Autowired
	private CredentialService credentialService;

	public static void main(String[] args) {

		SpringApplication.run(CmpApplication.class, args);
	}

	@PostConstruct
	public void init(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
		List<awsClient> clientList = (ArrayList<awsClient>)context.getBean("awsClients");
		awsClient client;

		for(AwsCredentials awsCredential : credentialService.getAwsCredentials()){
			client = new awsClient(awsCredential.getAccessKey(),awsCredential.getSecretKey());
            clientList.add(client);
		}
	}

}
