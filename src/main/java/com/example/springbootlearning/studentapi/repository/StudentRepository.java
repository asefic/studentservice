package com.example.springbootlearning.studentapi.repository;

import com.example.springbootlearning.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
