package com.example.cinemarate.Configutaion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    /**
     * you make HTTP requests to APIs or services
     * @return
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();

    }}
