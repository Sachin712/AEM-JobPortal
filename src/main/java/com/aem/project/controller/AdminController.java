package com.aem.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

import com.aem.project.entity.Admin;

import com.aem.project.service.AdminService;

import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Log4j
public class AdminController {

	@Autowired
	private AdminService adminService;

	// To add a new admin
	@PostMapping("/admins")
	public ResponseEntity<String> addUser(@RequestBody Admin adminData) {
		adminService.addAdmin(adminData);
		log.info("Accessing post method... Admin added successfully.");
		return ResponseEntity.ok("Admin added!");

	}

	// Get all admins
	@GetMapping("/admins")
	public List<Admin> getAdmin() {
		return adminService.getAllAdmins();
	}

	// Get an admin by Id
	@GetMapping("/admins/{id}")
	public Optional<Admin> getAdmins(@PathVariable String id) {
		return adminService.findById(id);
	}

	// Updating admin info
	@PutMapping("/admins/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody Admin admin,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws IOException {

		String tempToken = token.split(" ")[0].trim();
		if (token.isEmpty() || token.equals(null) || !tempToken.equals("Bearer"))
			return new ResponseEntity<String>("Token null or empty", HttpStatus.UNAUTHORIZED);
		else {

			
			Optional<Admin> adminData = adminService.findById(id);
			if (adminData.isPresent()) {
				Admin adminInfo = adminData.get();
				Admin updateAdmin = adminService.updateAdmin(adminInfo, admin);
				log.info("Accessing put method... Admin updated successfully.");
				return new ResponseEntity<>(updateAdmin, HttpStatus.OK);
			} else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

}
