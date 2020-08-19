package com.microservice.EmployeeSearchApplication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="employee-client",configuration=FeignClientConfiguration.class)
public interface EmployeeServiceProxy {

    @GetMapping("/api/poc/employees/{id}")
    ResponseEntity<Object> searchEmployeeInfoById(@PathVariable(value="id") Long id);
}
