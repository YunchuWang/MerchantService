package com.project.passbook.merchantService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = {"com.project.passbook.merchantService"})
@ComponentScan(basePackages = {"com.project.passbook.merchantService"})
@EntityScan(basePackages = {"com.project.passbook.merchantService"})
@SpringBootApplication
public class MerchantServiceApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(MerchantServiceApplication.class, args);
  }

}