package com.hsbc.test.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public void handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("HttpMediaTypeNotSupportedException", e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public void handleIllegalStateException(Throwable t) {
        log.error("IllegalStateException", t);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public void handleExceptionUnknown(Exception e) {
        log.error("Exception", e);
    }
}
