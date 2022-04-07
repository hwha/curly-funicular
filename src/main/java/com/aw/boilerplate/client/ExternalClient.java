package com.aw.boilerplate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "external", fallbackFactory = ExternalClientFallback.class)
public interface ExternalClient {

    @GetMapping("/news/error")
    String testError();

    @GetMapping("/news/delay")
    String testDelay();
}