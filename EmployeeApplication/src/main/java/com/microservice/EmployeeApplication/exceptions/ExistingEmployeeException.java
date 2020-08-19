package com.microservice.EmployeeApplication.exceptions;

public class ExistingEmployeeException extends RuntimeException {
    public ExistingEmployeeException(String message){
        super(message);
    }
}
