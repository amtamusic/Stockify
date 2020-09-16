package com.dismoor.andytech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockifyApplication.class, args);
	}

}
