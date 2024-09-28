package com.jaga.epicmomentoapi.service;

import com.jaga.epicmomentoapi.bean.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employeeDetails);
    void deleteEmployee(Long id);
}
