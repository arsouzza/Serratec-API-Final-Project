package br.com.ecommerce.config;

import org.springframework.context.annotation.Bean;



import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@Bean
	BCryptPasswordEncoder criptografar() {
		return new BCryptPasswordEncoder();
	}
	
	

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
