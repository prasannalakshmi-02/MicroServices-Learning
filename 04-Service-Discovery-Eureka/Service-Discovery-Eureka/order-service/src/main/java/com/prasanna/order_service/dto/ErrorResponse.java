package com.prasanna.order_service.dto;

import java.time.LocalDateTime;


public class ErrorResponse {

    private String message;
    private String statusCode;
    private String timeStamp;

    public ErrorResponse(){

    }

    public ErrorResponse(String message, String statusCode, String timeStamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
