package com.aw.boilerplate.controller;

import com.aw.boilerplate.client.ExternalClient;
import com.aw.boilerplate.client.GoogleClient;
import com.aw.boilerplate.dto.SampleResponse;
import com.aw.boilerplate.model.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/test")
@Slf4j
@RequiredArgsConstructor
public class TestController {
    private final ExternalClient externalClient;
    private final GoogleClient googleClient;

    @Operation(description = "Feign 딜레이 폴백 테스트") // feign fallback test
    @PostMapping(value ="/feign-delay")
    public ApiResponse<SampleResponse> testDelayFeign() throws Exception {
        externalClient.testDelay();

        return ApiResponse.success(null);
    }

    @Operation(description = "Feign 에러 결과 폴백 테스트") // feign fallback test
    @PostMapping(value ="/feign-error")
    public ApiResponse<SampleResponse> testErrorFeign() throws Exception {
        externalClient.testError();

        return ApiResponse.success(null);
    }

    @Operation(description = "구글호출테스트")
    @PostMapping(value ="/google")
    public ApiResponse<SampleResponse> testGoogle() throws Exception {
        log.info("[testGoogle] start");
        String result = googleClient.getGoogleMain();
        log.info("[testGoogle] finish, result => {}", result);

        return ApiResponse.success(null);
    }
}
