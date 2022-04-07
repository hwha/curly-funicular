package com.aw.boilerplate.exception;

import com.aw.boilerplate.enums.CustomExceptionCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final CustomExceptionCode code;

    public CustomException(CustomExceptionCode code) {
        this.code = code;
    }
    public int getCode() {
        return code.getCode();
    }
    public String getMessage() {
        return code.getMessage();
    }
}
