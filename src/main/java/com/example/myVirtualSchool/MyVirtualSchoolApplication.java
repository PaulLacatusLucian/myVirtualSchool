package com.example.myVirtualSchool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;

@SpringBootApplication(exclude = {ReactiveSecurityAutoConfiguration.class })
public class MyVirtualSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyVirtualSchoolApplication.class, args);
	}

}
