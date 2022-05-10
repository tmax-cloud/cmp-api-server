package com.tmax.cmp;

import com.tmax.cmp.configuration.ClientConfig;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CmpApplication {

	public static void main(String[] args) {

		SpringApplication.run(CmpApplication.class, args);
	}

}
