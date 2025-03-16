package com.example.springbootlearning.studentapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private int age;

    @Column(unique = true)
    private String username; // New field for login

    private String password; // S


}
