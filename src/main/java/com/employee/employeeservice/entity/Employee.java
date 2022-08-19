package com.employee.employeeservice.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Employee {
    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false)
    private Long id;
    @Column(name = "EMPLOYEE_FIRSTNAME", nullable = false)
    private String empFirstName;
    @Column(name = "EMPLOYEE_LASTNAME", nullable = false)
    private String empLastName;

}
