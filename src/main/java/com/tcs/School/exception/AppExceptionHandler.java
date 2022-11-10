package com.tcs.School.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleStudentNotFound(StudentNotFound ex, WebRequest request){
        return new ResponseEntity<>(
                new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }
}