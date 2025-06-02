package com.taskbackend1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskbackend1.model.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByWingId(Long wingId);
    
    
}
