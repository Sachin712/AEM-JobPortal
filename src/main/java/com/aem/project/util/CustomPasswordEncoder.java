package com.aem.project.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//Accesses the PasswordEncoder class for bcrypting out password
@Component
public class CustomPasswordEncoder {
	private PasswordEncoder passwordEncoder;

	public CustomPasswordEncoder() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public String encryptPassword(String password) {
		return passwordEncoder.encode(password);
	}
}
