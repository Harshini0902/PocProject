package com.microservice.EmployeeApplication.service.impl;

import com.microservice.EmployeeApplication.dao.EmployeeDao;
import com.microservice.EmployeeApplication.model.Employee;
import com.microservice.EmployeeApplication.resource.EmployeeResource;
import com.microservice.EmployeeApplication.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * This method is used to get all employees.
     *
     * @return List of employees
     */
    @Override
    public List<Employee> getAllEmployees() {

        logger.info("Inside get all employees method :: start");

        List<Employee> employeeList=employeeDao.getAllEmployees();

        logger.info("Inside get all employees method :: end");
        return employeeList;

    }

    /**
     * This method is used to get employee by id.
     * @param id
     * @return Employee object
     */
    @Override
    public Employee getEmployeeById(Long id) {

        logger.info("Inside getEmployeeById method :: start");

        Employee employee=employeeDao.getEmployeeById(id);

        logger.info("Inside getEmployeeById method :: end");
        return employee;

    }

    /**
     * This method is used to create employee.
     * @param employee
     * @return Employee object
     */
    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("Inside createEmployee method :: start");

        Employee createdEmployee=employeeDao.createEmployee(employee);

        logger.info("Inside createEmployee method :: end");
        return createdEmployee;
    }

    /**
     * This method is used to update employee.
     * @param id
     * @param employee
     * @return Employee object
     */
    @Override
    public Employee updateEmployee(Long id,Employee employee) {
        logger.info("Inside updateEmployee method :: start");

        Employee updatedEmployee=employeeDao.updateEmployee(id,employee);

        logger.info("Inside updateEmployee method :: end");
        return updatedEmployee;
    }

    /**
     * This method is used to delete employee.
     * @param id
     * @return boolean variable
     */
    @Override
    public boolean deleteEmployee(Long id) {
        logger.info("Inside deleteEmployee method :: start");

       boolean deleted=employeeDao.deleteEmployee(id);

       logger.info("Inside deleteEmployee method :: end");
       return deleted;

    }
}
