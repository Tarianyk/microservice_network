package com.epam.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ProfileNotFoundException extends RuntimeException {
    private String message;
    private HttpStatus status;
}
