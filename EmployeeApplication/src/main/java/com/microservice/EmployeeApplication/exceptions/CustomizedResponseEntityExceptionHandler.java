package com.microservice.EmployeeApplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest req){

        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistingEmployeeException.class)
    public final ResponseEntity<ExceptionResponse> handleExistingEmployeeException(ExistingEmployeeException ex, WebRequest req){

        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
    }
}
