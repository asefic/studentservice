package com.example.springbootlearning.studentapi.repository;

import com.example.springbootlearning.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);


    List<Student> findByAge(int age);

    List<Student> findByNameContaining(String name);

    List<Student> findByNameContainingIgnoreCase(String name);

    Optional<Student> findByUsername(String username);



}
