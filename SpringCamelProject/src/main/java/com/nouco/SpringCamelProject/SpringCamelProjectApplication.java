package com.nouco.SpringCamelProject;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCamelProjectApplication {

	@Autowired
	ProducerTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(SpringCamelProjectApplication.class, args);
	}

}
