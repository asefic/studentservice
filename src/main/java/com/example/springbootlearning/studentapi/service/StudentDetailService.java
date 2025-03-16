package com.example.springbootlearning.studentapi.service;

import com.example.springbootlearning.studentapi.model.Student;
import com.example.springbootlearning.studentapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentDetailService implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Student student = studentRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username) );

        User userDetailsResponse = new User(student.getUsername(),student.getPassword(),new ArrayList<>());
        return userDetailsResponse;
    }
}
