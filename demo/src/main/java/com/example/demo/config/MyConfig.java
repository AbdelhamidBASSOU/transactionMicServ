package com.example.demo.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public HttpMessageConverters httpMessageConverters() {
        return new HttpMessageConverters();
    }
}
