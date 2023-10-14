package com.example.awsecs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.devh.boot.grpc.server.autoconfigure.GrpcServerSecurityAutoConfiguration;

@SpringBootApplication(exclude = {GrpcServerSecurityAutoConfiguration.class})

public class AwsEcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsEcsApplication.class, args);
	}

}
