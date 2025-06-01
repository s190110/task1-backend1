package com.taskbackend1.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import com.taskbackend1.model.Department;
import com.taskbackend1.model.Wing;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private Integer age;
    private LocalDate dateOfJoined;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "employee_skills",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;
    
    @ManyToOne
    @JoinColumn(name = "wing_id")
    private Wing wing;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
