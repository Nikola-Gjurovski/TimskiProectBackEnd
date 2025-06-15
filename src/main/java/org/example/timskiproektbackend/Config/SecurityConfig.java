package org.example.timskiproektbackend.Config;

import org.example.timskiproektbackend.Repository.EmployeeRepository;
import org.example.timskiproektbackend.Service.EmployeeDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // recommended way in 6.1+
                .formLogin(form -> form
                        .defaultSuccessUrl("http://localhost:5173", true) // Redirect after login
                        .permitAll() // Allow public access to the login page
                ).cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(EmployeeRepository repository) {
        return new EmployeeDetailsService(repository);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Allow all endpoints
                        .allowedOrigins("http://localhost:5173") // Allow requests from React frontend
                        .allowedMethods("*")  // Allow all HTTP methods (GET, POST, etc.)
                        .allowCredentials(true); // Allow cookies (if needed)
            }
        };
    }

}