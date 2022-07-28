package com.aem.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aem.project.entity.Applicant;
import com.aem.project.repository.ApplicantRepository;
import com.aem.project.util.CustomPasswordEncoder;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomPasswordEncoder customPasswordEncoder;

	@Autowired
	private ApplicantRepository applicantRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		Optional<Applicant> applicantOpt = applicantRepo.findByUsername(username);

		return applicantOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid credentials!"));
	}

}
