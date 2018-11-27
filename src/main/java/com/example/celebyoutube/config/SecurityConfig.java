package com.example.celebyoutube.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic();

        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE).fullyAuthenticated()
                .antMatchers(HttpMethod.POST).fullyAuthenticated()
//                .antMatchers(HttpMethod.PUT).fullyAuthenticated()
                .anyRequest().permitAll();

        http.csrf().disable();
    }
}
