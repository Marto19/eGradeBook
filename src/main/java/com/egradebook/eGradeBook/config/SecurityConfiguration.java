package com.egradebook.eGradeBook.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration
{
    private final DaoAuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {

         return http.authorizeRequests((authorize) -> authorize
                         .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/admin/**").hasAuthority("admin")
                         .requestMatchers(HttpMethod.GET, "/roles").hasAuthority("admin")
                         .requestMatchers(HttpMethod.GET, "/absences").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password"))
                .logout(logout -> logout
                        .permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login"))
                 .authenticationProvider(authenticationProvider)
                .build();
    }
}
