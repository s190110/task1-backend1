package com.taskbackend1.model;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Min(value = 0, message = "Age must be positive")
    private Integer age;

    @NotNull(message = "Date of joining is required")
    @PastOrPresent(message = "Date of joining cannot be in the future")
    private LocalDate dateOfJoined;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Wing is required")
    @ManyToOne
    @JoinColumn(name = "wing_id")
    private Wing wing;

    @NotNull(message = "Department is required")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @NotNull(message = "Has Experience is required")
    private Boolean hasExperience;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Experience> experiences = new ArrayList<>();

    private String totalExperience;

    @Lob
    @Column(name = "photo", columnDefinition = "TEXT")
    @NotBlank(message = "Photo is required")
    @Pattern(
      regexp = "^data:image\\/[^;]+;base64,[a-zA-Z0-9+/=\\r\\n]+$",
      message = "Photo must be a valid base64 image"
    )
    private String photo;


    @NotEmpty(message = "Skills are required")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "employee_skills",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills = new ArrayList<>();
}
