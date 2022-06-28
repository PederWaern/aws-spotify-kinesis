package com.hotmail.pederwaern.skinesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SkinesisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkinesisApplication.class, args);
	}

}
