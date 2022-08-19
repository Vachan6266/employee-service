package com.employee.employeeservice.service;

import com.employee.employeeservice.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IEmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long employeeId);
    Page<Employee> getAllEmployees(Integer page, Integer size);
    Employee updateEmployee(Employee employee);
    void deleteEmployeeById(Long employeeId);

}
