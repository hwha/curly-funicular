package com.aw.boilerplate.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GoogleClientFallback implements FallbackFactory<GoogleClient> {
    @Override
    public GoogleClient create(Throwable cause) {
        log.info("[GoogleClient] error occurred {}", cause.getMessage());
        return () -> "GoogleClient request fail";
    }
}