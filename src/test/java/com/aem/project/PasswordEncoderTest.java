package com.aem.project;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aem.project.util.CustomPasswordEncoder;

public class PasswordEncoderTest {

	@Test
	public void encode_password() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		CustomPasswordEncoder cpe = new CustomPasswordEncoder();
		System.out.println(encoder.encode("Sachin1234"));
		String pass = "asdfasf";
		System.out.println("method: " + cpe.encryptPassword(pass));
	}
}
