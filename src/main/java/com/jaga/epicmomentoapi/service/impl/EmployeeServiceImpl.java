package com.jaga.epicmomentoapi.service.impl;

import com.jaga.epicmomentoapi.bean.Employee;
import com.jaga.epicmomentoapi.repository.EmployeeRepository;
import com.jaga.epicmomentoapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setAddress(employeeDetails.getAddress());
        employee.setMobileNumber(employeeDetails.getMobileNumber());
        employee.setDob(employeeDetails.getDob());
        employee.setEmail(employeeDetails.getEmail());
        employee.setLastLogIn(employeeDetails.getLastLogIn());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
