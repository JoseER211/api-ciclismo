package com.sofka.co.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotEqualsException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;

    public NotEqualsException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}

