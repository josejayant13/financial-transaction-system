package com.josejayant.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class TransactionAPIApplication {



	public static void main(String[] args) {

		SpringApplication.run(TransactionAPIApplication.class, args);

		System.out.println("------------App is online-------------");
//		test
	}

}
