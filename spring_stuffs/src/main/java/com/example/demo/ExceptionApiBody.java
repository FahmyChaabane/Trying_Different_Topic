package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExceptionApiBody {
    private HttpStatus httpStatus;
    private String errorMsg;
    private ZonedDateTime zonedDateTime;
}
