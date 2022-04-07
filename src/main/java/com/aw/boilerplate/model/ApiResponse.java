package com.aw.boilerplate.model;

import lombok.Getter;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    private final int code;
    private final String message;
    private final T data;
    private final String traceId;
    private final String spanId;

    public ApiResponse(final int code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.traceId = MDC.get("traceId");
        this.spanId = MDC.get("spanId");
    }

    public static<T> ApiResponse<T> error(final int code, final String message) {
        return new ApiResponse<>(code, message, null);
    }

    public static<T> ApiResponse<T> success(final T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }
}
