package com.taskbackend1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Wing {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String name;

}
