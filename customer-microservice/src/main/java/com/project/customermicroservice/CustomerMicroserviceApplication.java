package com.project.customermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// For swagger
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.project.customermicroservice")
//@EnableDiscoveryClient
//@EnableSwagger2
public class CustomerMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerMicroserviceApplication.class, args);
    }

    //  TODO deploy Swagger
//    @Bean
//    public Docket swaggerApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("vmware.services.department.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(new ApiInfoBuilder().version("0.0.1").title("Customer Microservice API").description("Hold customer data").build());
//    }

}
