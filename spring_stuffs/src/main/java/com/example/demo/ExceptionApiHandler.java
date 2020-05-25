package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {OrderNameNotFoundException.class})
    public ResponseEntity<ExceptionApiBody> mapException(OrderNameNotFoundException e, WebRequest request) {
        ExceptionApiBody apiException = new ExceptionApiBody(
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {OrderListEmptyException.class})
    public ResponseEntity<ExceptionApiBody> mapListException(OrderListEmptyException e, WebRequest request) {
        ExceptionApiBody apiException = new ExceptionApiBody(
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }


}
