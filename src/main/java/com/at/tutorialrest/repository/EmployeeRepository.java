package com.at.tutorialrest.repository;

import com.at.tutorialrest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
