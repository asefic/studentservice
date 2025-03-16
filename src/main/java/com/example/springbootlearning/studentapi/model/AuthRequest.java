package com.example.springbootlearning.studentapi.model;

import lombok.Data;

@Data
public class AuthRequest{
	private String password;
	private String username;
}