package com.aw.boilerplate.client;

import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Import;

@RestClientTest(GoogleClient.class)
@Import({FeignAutoConfiguration.class})
public class GoogleClientTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GoogleClient googleClient;

    @Test
    public void getGoogleMainTest() throws InterruptedException {
        // given

        // when
        String responseBody = googleClient.getGoogleMain();
        log.info("apiResBody =>{}", responseBody);

        // then
        Assertions.assertTrue(responseBody.contains("<html"));
    }
}
