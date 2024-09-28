package com.jaga.epicmomentoapi.repository;

import com.jaga.epicmomentoapi.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByFirstName(String name);
    Employee findByEmail(String email);
}
