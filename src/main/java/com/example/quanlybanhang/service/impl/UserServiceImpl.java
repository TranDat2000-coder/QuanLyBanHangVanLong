package com.example.quanlybanhang.service.impl;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.quanlybanhang.dto.UserRegistrationDto;
import com.example.quanlybanhang.entites.Role;
import com.example.quanlybanhang.entites.User;
import com.example.quanlybanhang.repository.UserRepository;
import com.example.quanlybanhang.service.UserService;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		
		User user = new User();
		user.setFirstName(registrationDto.getFirstName());
		user.setLastName(registrationDto.getLastName());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Sai username hoặc password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(),
            mapRolesAuthorities(user.getRoles()));
    }
	
	public Collection < ? extends GrantedAuthority> mapRolesAuthorities(Collection<Role> roles){
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}
}
