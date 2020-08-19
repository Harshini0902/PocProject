package com.microservice.EmployeeSearchApplication;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/poc")
public class EmployeeSearchController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeSearchController.class);

    @Autowired
    EmployeeServiceProxy employeeServiceProxy;

    @GetMapping("/search-employee/{id}")
    @HystrixCommand(fallbackMethod= "defaultResponse",ignoreExceptions = {FeignException.class},commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public ResponseEntity searchEmployeeInfoById(@PathVariable("id") Long id) throws InterruptedException{

        logger.info("Inside searchEmployeeInfoById method :: start ");

//        Thread.sleep(3000);
        ResponseEntity employeeInfo=employeeServiceProxy.searchEmployeeInfoById(id);

        logger.info("Inside searchEmployeeInfoById method :: end ");
        return employeeInfo;
    }

    public ResponseEntity defaultResponse(Long id){
        logger.info("Inside defaultResponse method :: start ");

        String err = "Fallback error as the microservice is down.";

        logger.info("Inside defaultResponse method :: start ");
        return new ResponseEntity(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
