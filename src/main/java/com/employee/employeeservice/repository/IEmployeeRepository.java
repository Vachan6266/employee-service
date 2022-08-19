package com.employee.employeeservice.repository;

import com.employee.employeeservice.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    Page<Employee> findAll(Pageable pageable);
}
