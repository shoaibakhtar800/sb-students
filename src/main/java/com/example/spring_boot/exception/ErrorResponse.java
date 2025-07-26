package com.example.spring_boot.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private String error;
    private String message;
    private LocalDateTime timeStamp;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
