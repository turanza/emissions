package com.triply.emissions.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ErrorAuthResponse {
    HttpStatus httpStatus;
    String message;
}
