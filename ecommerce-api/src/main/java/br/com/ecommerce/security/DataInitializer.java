package br.com.ecommerce.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("sa").isEmpty()) {
                User user = new User();
                user.setUsername("sa");
                user.setPassword("{noop}sa");
                user.setRoles("ADMIN,USER");
                userRepository.save(user);
            }
        };
    }
}
