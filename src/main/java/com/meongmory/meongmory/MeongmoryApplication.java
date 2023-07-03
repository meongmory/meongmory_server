package com.meongmory.meongmory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MeongmoryApplication {

	public static void main(String[] args) {
		System.out.println("hello world!");
		SpringApplication.run(MeongmoryApplication.class, args);
	}

}
