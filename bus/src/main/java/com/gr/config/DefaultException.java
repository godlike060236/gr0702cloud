package com.gr.config;

import com.gr.util.ResultJson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultException {
    @ExceptionHandler
    public ResultJson defaultExceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultJson.error(null, e.getMessage());
    }
}
