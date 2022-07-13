package com.aem.project.controller;


import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import com.aem.project.entity.Admin;


import com.aem.project.service.AdminService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	private static final Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;
	
	//To add a new admin
	@PostMapping("/admins")
	public ResponseEntity<String> addUser(@RequestBody Admin adminData) {
		adminService.addAdmin(adminData);
		logger.info("Accessing post method... Admin added successfully.");
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
	public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody Admin admin) {
		Optional<Admin> userData = adminService.findById(id);
		if (userData.isPresent()) {
			Admin adminInfo = userData.get();
		adminInfo.setAdmin_name(admin.getAdmin_name());
		adminInfo.setAdmin_contact(admin.getAdmin_contact());
		adminInfo.setAdmin_email(admin.getAdmin_email());
		adminInfo.setAdmin_password(admin.getAdmin_password());
		adminInfo.setAdmin_username(admin.getAdmin_username());

			adminService.addAdmin(adminInfo);
			logger.info("Accessing put method... Admin updated successfully.");
			return ResponseEntity.ok("Admin Updated!");
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}



}
