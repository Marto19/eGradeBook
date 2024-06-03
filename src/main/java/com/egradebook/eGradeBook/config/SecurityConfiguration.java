package com.egradebook.eGradeBook.config;

import com.egradebook.eGradeBook.services.UserService;
import com.egradebook.eGradeBook.services.serviceImplementation.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests((authorize) -> authorize

                        .requestMatchers(HttpMethod.GET, "/admin").hasAuthority("admin")
                        .requestMatchers(HttpMethod.GET, "/").hasAuthority("user")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());


        return http.build();
    }

}
