package com.pridanov.mcs2.util.handlers;

import com.pridanov.mcs2.util.dto.ExceptionResponse;
import com.pridanov.mcs2.util.exceptions.BadParamException;
import com.pridanov.mcs2.util.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler()
    private ResponseEntity<ExceptionResponse> handleException(BadParamException e) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.toString(),
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    private ResponseEntity<ExceptionResponse> handleException(NotFoundException e) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.NOT_FOUND.toString(),
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
