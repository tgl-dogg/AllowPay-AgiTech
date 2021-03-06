package br.com.allowpay.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableJpaRepositories(basePackages="br.com.allowpay.repositories")
@EnableScheduling
public class AllowpayConfiguration {

}
