package com.tosslab.homework.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableAsync
@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "com.tosslab.homework.v1.config.properties")
public class V1Application {

	public static void main(String[] args) {
		SpringApplication.run(V1Application.class, args);
	}

}
