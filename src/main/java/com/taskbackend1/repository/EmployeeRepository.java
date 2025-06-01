package com.taskbackend1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskbackend1.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}

