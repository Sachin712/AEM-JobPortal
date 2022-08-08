package com.aem.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aem.project.entity.User;
import com.aem.project.repository.UserRepository;
import com.aem.project.util.CustomPasswordEncoder;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository applicantRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		Optional<User> applicantOpt = applicantRepo.findByUsername(username);

		return applicantOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid credentials!"));
	}

}
