package com.taskbackend1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskbackend1.model.Employee;
import com.taskbackend1.model.Skill;
import com.taskbackend1.repository.EmployeeRepository;
import com.taskbackend1.repository.SkillRepository;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private SkillRepository skillRepo;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        List<Skill> fullSkills = employee.getSkills().stream()
            .map(s -> skillRepo.findById(s.getId()).orElseThrow())
            .toList();

        employee.setSkills(fullSkills);
        return employeeRepo.save(employee);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

}
