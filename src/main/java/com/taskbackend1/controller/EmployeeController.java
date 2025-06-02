package com.taskbackend1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskbackend1.model.Department;
import com.taskbackend1.model.Employee;
import com.taskbackend1.model.Experience;
import com.taskbackend1.model.Skill;
import com.taskbackend1.model.Wing;
import com.taskbackend1.repository.DepartmentRepository;
import com.taskbackend1.repository.EmployeeRepository;
import com.taskbackend1.repository.SkillRepository;
import com.taskbackend1.repository.WingRepository;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private SkillRepository skillRepo;
    
    @Autowired
    private WingRepository wingRepo;

    @Autowired
    private DepartmentRepository deptRepo;



    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    	
    	System.out.println("hello loeksh ");
    	
//    +employee.getWing().getId());
    	
    	
    	Wing wing = wingRepo.findById(employee.getWing().getId()).orElseThrow();
    	Department dept = deptRepo.findById(employee.getDepartment().getId()).orElseThrow();
    	employee.setWing(wing);
    	employee.setDepartment(dept);
    	
        List<Skill> fullSkills = employee.getSkills().stream()
            .map(s -> skillRepo.findById(s.getId()).orElseThrow())
            .toList();
        
        if (employee.getExperiences() != null) {
            for (Experience exp : employee.getExperiences()) {
                exp.setEmployee(employee); 
            }
        }
        employee.setTotalExperience(employee.getTotalExperience());

        employee.setSkills(fullSkills);
        Employee savedEmployee = employeeRepo.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);    }

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
