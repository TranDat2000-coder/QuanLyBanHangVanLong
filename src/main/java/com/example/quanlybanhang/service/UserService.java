package com.example.quanlybanhang.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.quanlybanhang.dto.UserRegistrationDto;
import com.example.quanlybanhang.entites.User;

public interface UserService extends UserDetailsService {

	User findByEmail(String email);
	User save(UserRegistrationDto registrationDto);
}
