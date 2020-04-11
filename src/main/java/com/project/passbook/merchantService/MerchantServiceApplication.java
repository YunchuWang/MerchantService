package com.project.passbook.merchantService;

import com.project.passbook.merchantService.security.AuthenticationInterceptor;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = {"com.project.passbook.merchantService"})
@ComponentScan(basePackages = {"com.project.passbook.merchantService"})
@EntityScan(basePackages = {"com.project.passbook.merchantService"})
@SpringBootApplication
public class MerchantServiceApplication implements WebMvcConfigurer {

  @Resource
  private AuthenticationInterceptor authenticationInterceptor;

  public static void main(String[] args) {
    SpringApplication.run(MerchantServiceApplication.class, args);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authenticationInterceptor)
            .addPathPatterns("/**");
  }
}

@RestController
class ServiceInstanceRestController {

  @Autowired
  private DiscoveryClient discoveryClient;

  @RequestMapping("/service-instances/{applicationName}")
  public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
    return this.discoveryClient.getInstances(applicationName);
  }
}