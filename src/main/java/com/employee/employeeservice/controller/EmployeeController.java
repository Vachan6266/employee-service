package com.employee.employeeservice.controller;

import com.employee.employeeservice.entity.Employee;
import com.employee.employeeservice.exception.DataFormatException;
import com.employee.employeeservice.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.employee.employeeservice.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


@RestController
@RequestMapping("/v1/api/employees")
@AllArgsConstructor
@Api(tags = {"employees"})
public class EmployeeController {

    protected static final String  DEFAULT_PAGE_SIZE = "100";
    protected static final String DEFAULT_PAGE_NUM = "0";
    private final IEmployeeService employeeService;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a Employee resource.", notes = "Returns the URL of the new resource in the EmployeeId header.")
    public void createEmployee(@RequestBody Employee employee,
                            HttpServletRequest request, HttpServletResponse response) {
        Employee createdEmployee = this.employeeService.createEmployee(employee);
        response.setHeader("EmployeeID", request.getRequestURL().append("/").append(createdEmployee.getId()).toString());
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all employees.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<Employee> getAllEmployees(@ApiParam(value = "The page number (zero-based)", required = true)
                            @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                               @ApiParam(value = "Tha page size", required = true)
                            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return this.employeeService.getAllEmployees(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single employee.", notes = "You have to provide a valid employee ID.")
    public
    @ResponseBody
    Employee getEmployee(@ApiParam(value = "The ID of the employee.", required = true)
                   @PathVariable("id") Long id) {
        Employee employee = this.employeeService.getEmployeeById(id);
        checkResourceFound(employee);
        return employee;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a employee resource.", notes = "You have to provide a valid employee ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateEmployee(@ApiParam(value = "The ID of the existing employee resource.", required = true)
                            @PathVariable("id") Long id, @RequestBody Employee employee) {
        checkResourceFound(this.employeeService.getEmployeeById(id));
        if (!Objects.equals(id, employee.getId())) throw new DataFormatException("ID doesn't match!");
        this.employeeService.updateEmployee(employee);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a employee resource.", notes = "You have to provide a valid employee ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteEmployee(@ApiParam(value = "The ID of the existing employee resource.", required = true)
                            @PathVariable("id") Long id) {
        checkResourceFound(this.employeeService.getEmployeeById(id));
        this.employeeService.deleteEmployeeById(id);
    }

    public static <T> void checkResourceFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException("resource not found");
        }
    }
}
