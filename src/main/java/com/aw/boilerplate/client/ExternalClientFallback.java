package com.aw.boilerplate.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExternalClientFallback implements FallbackFactory<ExternalClient> {
    @Override
    public ExternalClient create(Throwable cause) {
        return new ExternalClient() {
            @Override
            public String testError() {
                log.info("[ExternalClient testError fallback] error occurred {}", cause.getMessage());
                return null;
            }

            @Override
            public String testDelay() {
                log.info("[ExternalClient testDelay fallback] error occurred {}", cause.getMessage());
                return null;
            }
        };
    }
}