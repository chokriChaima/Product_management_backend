package com.softparadigm.ProductManagement.security;

import ch.qos.logback.classic.Logger;
import com.softparadigm.ProductManagement.security.jwtAuth.JwtAuthFilter;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
// all things security
public class SecurityConfig {

    private final JwtAuthFilter JwtAuthFilter;
    private final UserDetailsServiceCustom userDetailsServiceCustom;


    @Autowired
    public SecurityConfig(com.softparadigm.ProductManagement.security.jwtAuth.JwtAuthFilter jwtAuthFilter, UserDetailsServiceCustom userDetailsServiceCustom) {
        JwtAuthFilter = jwtAuthFilter;
        this.userDetailsServiceCustom = userDetailsServiceCustom;

    }

    //basic authentication code
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests((authz) ->
                        authz
                                .requestMatchers("/api/secure/**")
                                .authenticated()
                                .requestMatchers("/api/local-users/users-list")
                                .authenticated().requestMatchers("/api/facebook-users/users-list")
                                .authenticated()
                                .anyRequest()
                                .permitAll()

                ).sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(JwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        // To see the filters applied to this request
//        System.out.println(this.filterChain(http).getFilters().toString());
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsServiceCustom);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        System.out.println("Authentication Provider create");
        return authenticationProvider;
    }

    @Bean
        // Do not use in production but its okay for development even if its duoricated
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}