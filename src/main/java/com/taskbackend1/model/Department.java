package com.taskbackend1.model;

import jakarta.persistence.*;
import lombok.*;



@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "wing_id")
    private Wing wing;

}
