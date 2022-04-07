package com.aw.boilerplate.enums;

import lombok.Getter;

public enum CustomExceptionCode {
    // 1000 ~ 1999 : Sample
    SAMPLE_API_RESPONSE_EMPTY(1000, "샘플 조회 결과가 없음", null),
    SAMPLE_REQUIRED_FIELD_ERROR(1001, "샘플 필요 파라미터 값 누락", null);

    private final Integer code;
    private final String message;
    private final Integer httpResponseState;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getHttpResponseState() {
        return httpResponseState;
    }

    CustomExceptionCode(Integer code, String message, Integer httpResponseState) {
        this.code = code;
        this.message = message;
        this.httpResponseState = httpResponseState;
    }
}
