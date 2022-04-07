package com.aw.boilerplate.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${gateway.endpoint}/${spring.application.name}")
    private String gatewayEndpoint;

    @Bean
    public OpenAPI springShopOpenAPI() {
        List<Server> serverList = new ArrayList<Server>();
        // dev-gateway config
        serverList.add(new Server()
                .url(gatewayEndpoint)
                .description("gateway"));
        // local config
        serverList.add(new Server()
                .url("http://localhost:8080")
                .description("local"));
        return new OpenAPI()
                .info(new Info().title(applicationName + " API"))
                .servers(serverList);
    }
}
