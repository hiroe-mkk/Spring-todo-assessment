package com.example.todo.common;

import com.example.todo.common.logging.UserIdMDCPutFilter;
import com.example.todo.domain.service.account.AccountSharedService;
import com.example.todo.domain.service.userdetails.SampleUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toStaticResources;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationProvider authenticationProvider,
                                                   UserIdMDCPutFilter userIdMDCPutFilter) throws Exception {
        http
                .formLogin(form -> form
                        .loginPage("/login/loginForm")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login/loginForm?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/"))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/login/**", "/error/**").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .addFilterAfter(userIdMDCPutFilter, AnonymousAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService(AccountSharedService accountSharedService) {
        return new SampleUserDetailsService(accountSharedService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
