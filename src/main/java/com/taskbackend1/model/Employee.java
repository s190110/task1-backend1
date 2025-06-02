package com.taskbackend1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	@JoinTable(name = "employee_skills", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<Skill> skills;

	@ManyToOne
	@JoinColumn(name = "wing_id")
	private Wing wing;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Experience> experiences = new ArrayList<>();
	
    private String totalExperience;




}
