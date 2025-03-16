package com.example.springbootlearning.studentapi.service;

import com.example.springbootlearning.studentapi.exception.StudentNotFoundException;
import com.example.springbootlearning.studentapi.model.Student;
import com.example.springbootlearning.studentapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }

    public Student saveStudent(Student student) {
        return (Student) studentRepository.save(student);
    }

    public boolean deleteStudentById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public Student updateStudent(Long id,Student studentDetails) {
        //check if student exist
        Student student = getStudentById(id);


        //update student information

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setAge(studentDetails.getAge());

        return studentRepository.save(student);
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);

//        if (students.isEmpty()) {
//            throw new StudentNotFoundException("Student not found");
//        }


        return students;
    }

    public Student registerStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

}
