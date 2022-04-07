package com.aw.boilerplate.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "google", url = "${google-client.url}",
        configuration = GoogleClientConfig.class, fallbackFactory = GoogleClientFallback.class)
public interface GoogleClient {
    @GetMapping()
    String getGoogleMain(
            //@RequestParam(name = "q") String q
    );
}