package br.com.ecommerce.config;

import org.springframework.context.annotation.Bean;



import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
=======
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71

@Configuration
public class AppConfig {

	@Bean
	BCryptPasswordEncoder criptografar() {
		return new BCryptPasswordEncoder();
	}
<<<<<<< HEAD
	
	

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
=======
>>>>>>> f5c82d88e7d3da48b09ba93edd5c56a7d5410f71

}
