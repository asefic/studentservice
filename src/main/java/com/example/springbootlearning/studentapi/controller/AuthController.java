package com.example.springbootlearning.studentapi.controller;

import com.example.springbootlearning.studentapi.config.JwtUtil;
import com.example.springbootlearning.studentapi.model.AuthRequest;
import com.example.springbootlearning.studentapi.model.Student;
import com.example.springbootlearning.studentapi.model.User;
import com.example.springbootlearning.studentapi.repository.StudentRepository;
import com.example.springbootlearning.studentapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid Credentials"));
        }

        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Student request) {
        // Check if user already exists
        if (studentRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(400).body(Map.of("error", "Username already exists"));
        }

        // ✅ Save a new Student entity with the hashed password
        Student student = new Student();
        student.setUsername(request.getUsername());
        student.setPassword(passwordEncoder.encode(request.getPassword())); // Hash password
        student.setName(request.getName()); // Ensure name is passed in request
        student.setAge(request.getAge());   // Ensure age is passed in request

        studentRepository.save(student); // ✅ Now it will be saved in the database

        return ResponseEntity.ok(Map.of("message", "User registered successfully!"));
    }



}
