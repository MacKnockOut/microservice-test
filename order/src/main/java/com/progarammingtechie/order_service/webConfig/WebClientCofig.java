package com.progarammingtechie.order_service.webConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientCofig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
