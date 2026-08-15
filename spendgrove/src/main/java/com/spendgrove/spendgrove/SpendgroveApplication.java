package com.spendgrove.spendgrove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //triggers spring to scans your com.spendgrove.spendgrove package
public class SpendgroveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpendgroveApplication.class, args);
	}

}
