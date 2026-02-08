package com.example.demo.Exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String message;
    private String details;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() { return message; }
    public String getDetails() { return details; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
