package com.microservice.EmployeeApplication.resource;

import com.microservice.EmployeeApplication.exceptions.EmployeeNotFoundException;
import com.microservice.EmployeeApplication.exceptions.ExistingEmployeeException;
import com.microservice.EmployeeApplication.model.Employee;
import com.microservice.EmployeeApplication.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/poc")
public class EmployeeResource {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeResource.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * This method is used to fetch all employees.
     *
     * @return ResponseEntity List of employees
     */
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logger.info("Inside getAllEmployees method :: start");

        List<Employee> employees = employeeService.getAllEmployees();

        logger.info("Inside getAllEmployees method :: end");
        return new ResponseEntity(employees,HttpStatus.OK);
    }

    /**
     * This method is used to fetch employee by id.
     *
     * @return ResponseEntity of employee
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        logger.info("Inside getEmployeeById method :: start");

        Employee employee = employeeService.getEmployeeById(id);

        if(employee == null){
            throw new EmployeeNotFoundException("Employee of id "+ id + " not found");
        }

        logger.info("Inside getEmployeeById method :: end");
        return new ResponseEntity(employee, HttpStatus.OK);
    }

    /**
     * This method is used to create employee.
     *
     * @return ResponseEntity with updated object and created status.
     */
    @PostMapping("/employees")
    public ResponseEntity createEmployee(@RequestBody Employee employee) {
        logger.info("Inside getEmployeeById method :: start");

        if (employee.getId() == null) {
            logger.info("Inside getEmployeeById method :: end");
            return new ResponseEntity("Please provide id in the request body", HttpStatus.BAD_REQUEST);
        } else {
            Employee updatedEmployee = employeeService.createEmployee(employee);

            if (updatedEmployee == null) {
                throw new ExistingEmployeeException("Employee with id " + employee.getId() + " already exists");
            }

            logger.info("Inside getEmployeeById method :: end");
            return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.CREATED);
        }
    }
    /**
     * This method is used to update employee.
     *
     * @return ResponseEntity with updated object.
     */
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,@RequestBody Employee employee) {
        logger.info("Inside updateEmployee method :: start");


        Employee updatedEmployee = employeeService.updateEmployee(id,employee);

        if(updatedEmployee ==  null){
            throw  new EmployeeNotFoundException("Employee with id " + employee.getId() + " not found");
        }

        logger.info("Inside updateEmployee method :: end");
        return new ResponseEntity(updatedEmployee, HttpStatus.OK);
    }

    /**
     * This method is used to delete employee.
     *
     * @return ResponseEntity with updated object.
     */
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {
        logger.info("Inside updateEmployee method :: start");

        String success="Deleted successfully";
        boolean deleted = employeeService.deleteEmployee(id);

        if(deleted ==  false){
            throw  new EmployeeNotFoundException("Employee with id " + id + " not found");
        }

        logger.info("Inside updateEmployee method :: end");
        return new ResponseEntity(success, HttpStatus.OK);
    }

}
