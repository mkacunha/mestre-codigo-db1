package com.mkacunha.processadorcep.infrastructure.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityContextConfiguration {

    private final Map<String, User> loggedUsers = new HashMap<>();

    @Bean
    Map<String, User> loggedUsers() {
        return loggedUsers;
    }

    @Bean
    public FilterRegistrationBean securityContextFilter() {
        final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new SecurityContextFilter());
        filterRegBean.addUrlPatterns("/api/no-verify*");
        filterRegBean.setEnabled(Boolean.TRUE);
        filterRegBean.setName("Meu Filter");
        filterRegBean.setAsyncSupported(Boolean.TRUE);
        return filterRegBean;
    }
}
