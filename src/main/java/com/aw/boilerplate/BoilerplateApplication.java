package com.aw.boilerplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@Slf4j
@EnableCaching
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication (
        exclude = {
                MongoAutoConfiguration.class, MongoDataAutoConfiguration.class
        }
)
public class BoilerplateApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BoilerplateApplication.class, args);
    }

}

