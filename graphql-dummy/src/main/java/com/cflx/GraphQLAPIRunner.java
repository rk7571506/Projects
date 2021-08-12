package com.cflx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphQLAPIRunner {

	public static void main(String[] args) {
		SpringApplication.run(GraphQLAPIRunner.class, args);
		System.out.println("Server Started..");
	}

}
