package com.llit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = "com.llit.mapper")
public class CheapAirApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(CheapAirApplication.class, args);
	}
}


