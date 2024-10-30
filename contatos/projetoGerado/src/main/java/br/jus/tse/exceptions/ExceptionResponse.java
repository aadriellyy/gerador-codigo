
package br.jus.tse.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd hh:mm:ss")
    private LocalDateTime timeStamp;
    private String message;

    public ExceptionResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public ExceptionResponse(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ExceptionResponse() {
        timeStamp = LocalDateTime.now();
    }

    public ExceptionResponse(HttpStatus status, LocalDateTime timeStamp, String message) {
        this();
        this.status = status;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status){
        this.status = status;
    }

    public LocalDateTime getTimeStamp(){
        return timeStamp;
    }

    public void setTimeStamp( LocalDateTime timeStamp){
        this.timeStamp = timeStamp;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

}