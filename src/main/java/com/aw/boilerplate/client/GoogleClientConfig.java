package com.aw.boilerplate.client;

import feign.RequestInterceptor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
public class GoogleClientConfig {

    @Value("${google-client.auth-token}")
    private String authToken;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return restTemplate -> restTemplate.header("x-api-key", authToken.trim());
    }
}