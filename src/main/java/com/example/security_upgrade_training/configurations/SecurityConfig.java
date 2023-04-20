package com.example.security_upgrade_training.configurations;

import com.example.security_upgrade_training.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {
    private final CustomUserDetailsService userDetailsService;    //logics how to load users, in @RequiredArgsConstructor


    //PasswordEncoder bean (8 - strength level of password)

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


    // Method for authentification, we use emails, wrote logics in CustomUserDetailsService

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/", "/login", "/product/**", "/images/**", "/registration")
                .permitAll()                                                    //pages above are available to all
                .anyRequest().authenticated()                                  //for other pages login is needed
                .and()
                .formLogin()                                                  //we configure login form
                .loginPage("/login")                                         //place where login form will be available
                .permitAll()                                                //login page available to all
                .and()
                .logout()                                                  //we configure logout
                .permitAll();                                             //available to all

        return http.build();
    }





//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

}
