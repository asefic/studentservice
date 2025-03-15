package com.example.springbootlearning.studentapi.controller;

import com.example.springbootlearning.studentapi.model.Student;
import com.example.springbootlearning.studentapi.service.StudentService;
import jdk.security.jarsigner.JarSigner;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
       Student student = studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteStudentById(@PathVariable Long id) {
        boolean isDeleted = studentService.deleteStudentById(id);
        Map<String,Object> responseJSon = new HashMap<>();

        if (isDeleted) {
            responseJSon.put("status", "success");

            return ResponseEntity.ok(responseJSon);
        }else {
            responseJSon.put("status", "fail");
            return ResponseEntity.status(404).body(responseJSon);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable Long id,@RequestBody Student studentDetails) {
        Student student = studentService.updateStudent(id,studentDetails);


        return ResponseEntity.ok(student);
    }

    @PostMapping("/searchByName")
    public List<Student> getStudentsByName(@RequestBody String requestBody) {

        JSONObject jsonObject = new JSONObject(requestBody);
        String name = jsonObject.optString("name");
        List<Student> students = studentService.getStudentsByName(name);
        return students;
    }





}
