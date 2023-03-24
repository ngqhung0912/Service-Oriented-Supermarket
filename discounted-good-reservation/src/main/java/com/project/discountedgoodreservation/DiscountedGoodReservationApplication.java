package com.project.discountedgoodreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DiscountedGoodReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscountedGoodReservationApplication.class, args);
	}

}
