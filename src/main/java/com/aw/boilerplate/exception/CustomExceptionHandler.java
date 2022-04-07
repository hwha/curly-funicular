package com.aw.boilerplate.exception;

import com.aw.boilerplate.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RestControllerAdvice
public class CustomExceptionHandler implements ErrorController {

    @ExceptionHandler(CustomException.class)
    public ApiResponse<Void> handleBindCustomException(CustomException ex) {
        return ApiResponse.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleBindException(Exception ex) {
        return ApiResponse.error(500, ex.getMessage());
    }

    @RequestMapping("/error")
    public ResponseEntity<ApiResponse<Void>> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = Integer.parseInt(status.toString());
        log.info("Servlet Error occurred, status code => {}", statusCode);
        return ResponseEntity.status(statusCode).body(ApiResponse.error(statusCode, HttpStatus.resolve(statusCode).getReasonPhrase()));
    }
}
