package com.project.passbook.merchantService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.project.passbook.merchantService" })
@ComponentScan(basePackages = { "com.project.passbook.merchantService" })
@EntityScan(basePackages = { "com.project.passbook.merchantService" })
@SpringBootTest
class MerchantServiceApplicationTests {

	@Test
	void contextLoads() {
	}
}
