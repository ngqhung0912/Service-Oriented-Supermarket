package com.project.eshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EShoppingApplication {

  public static void main(String[] args) {
    SpringApplication.run(EShoppingApplication.class, args);
  }

}
