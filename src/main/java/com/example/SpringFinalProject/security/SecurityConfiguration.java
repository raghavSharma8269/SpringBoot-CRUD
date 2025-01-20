package com.example.SpringFinalProject.security;

import com.example.SpringFinalProject.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(
                                    "/createnewuser",
                                    "/userlogin",
                                    "/product/{id}",
                                    "/products",
                                    "/error"
                            )
                            .permitAll();

                    // User endpoints (ADMIN can also access these)
                    authorize.requestMatchers("/updateproduct").hasAnyAuthority("USER", "ADMIN");
                    authorize.requestMatchers("/createproduct").hasAnyAuthority("USER", "ADMIN");

                    // Admin-only endpoints
                    authorize.requestMatchers("/deleteuser").hasAuthority("ADMIN");
                    authorize.requestMatchers("/deleteproduct").hasAuthority("ADMIN");

                    authorize.requestMatchers("/getusers").hasAuthority("ADMIN");
                    authorize.requestMatchers("/getuser/{username}").hasAuthority("ADMIN");

                    authorize.requestMatchers("/error").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
