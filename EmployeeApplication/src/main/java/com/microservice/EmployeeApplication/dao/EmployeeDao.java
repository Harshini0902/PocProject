package com.microservice.EmployeeApplication.dao;

import com.microservice.EmployeeApplication.exceptions.EmployeeNotFoundException;
import com.microservice.EmployeeApplication.exceptions.ExistingEmployeeException;
import com.microservice.EmployeeApplication.model.Employee;
import com.microservice.EmployeeApplication.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDao {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * This method is used to get all employees.
     *
     * @return List of Employees
     */
    public List<Employee> getAllEmployees() {

        logger.info("Inside getAllEmployees method :: start");

        List<Employee> employeeList = employeeRepository.findAll();

        logger.info("Inside getAllEmployees method :: end");
        return employeeList;

    }

    /**
     * This method is used to get employee by id.
     * @param id
     * @return Employee object.
     */
    public Employee getEmployeeById(Long id) {
        logger.info("Inside getEmployeeById method :: start");

        Employee employee=null;

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            employee= employeeOptional.get();
        }

        logger.info("Inside getEmployeeById method :: end");
        return employee;
    }

    /**
     * This method is used to create employee.
     * @param employee
     * @return Employee object.
     */
    public Employee createEmployee(Employee employee) {
        logger.info("Inside createEmployee method :: start");

        Employee createdEmployee=null;

        boolean exists = employeeRepository.existsById(employee.getId());

        if (!exists) {
            createdEmployee = employeeRepository.save(employee);

        }

        logger.info("Inside createEmployee method :: end");
        return createdEmployee;
    }

    /**
     * This method is used to update employee.
     * @param id
     * @param employee
     * @return Employee object.
     */
    public Employee updateEmployee(Long id,Employee employee) {
        logger.info("Inside updateEmployee method :: start");

        Employee updatedEmployee=null;

        Optional<Employee> optionalEmployee=employeeRepository.findById(id);

        if(optionalEmployee.isPresent()){
            employee.setId(id);
            updatedEmployee=employeeRepository.save(employee);

        }

        logger.info("Inside updateEmployee method :: end");
        return updatedEmployee;
    }

    /**
     * This method is used to delete employee.
     * @param id
     * @return boolean variable
     */
    public boolean deleteEmployee(Long id) {
        logger.info("Inside deleteEmployee method :: start");

        boolean deleted=false;
        boolean found=employeeRepository.existsById(id);

        if(found) {
            employeeRepository.deleteById(id);
             deleted=true;
        }

        logger.info("Inside updateEmployee method :: end");
        return deleted;

    }
}

