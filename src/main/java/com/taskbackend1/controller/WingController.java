package com.taskbackend1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskbackend1.model.Department;
import com.taskbackend1.model.Wing;
import com.taskbackend1.repository.DepartmentRepository;
import com.taskbackend1.repository.WingRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class WingController {

    @Autowired
    private WingRepository wingRepo;

    @Autowired
    private DepartmentRepository deptRepo;

    @GetMapping("/wings")
    public List<Wing> getWings() {
        return wingRepo.findAll();
    }

    @GetMapping("/wings/{wingId}/departments")
    public List<Department> getDepartments(@PathVariable Long wingId) {
        return deptRepo.findByWingId(wingId);
    }
}
