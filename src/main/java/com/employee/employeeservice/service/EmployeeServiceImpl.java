package com.employee.employeeservice.service;

import com.employee.employeeservice.entity.Employee;
import com.employee.employeeservice.exception.ResourceNotFoundException;
import com.employee.employeeservice.repository.IEmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService{
    private final IEmployeeRepository employeeRepository;
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found"));
    }

    @Override
    public Page<Employee> getAllEmployees(Integer page, Integer size) {
        return employeeRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
