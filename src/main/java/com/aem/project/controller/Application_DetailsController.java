package com.aem.project.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aem.project.entity.Application_Details;
import com.aem.project.service.Application_DetailsService;

import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Log4j
public class Application_DetailsController {
	@Autowired
	Application_DetailsService app_DetailsService;

	// Add a new application detail
	@PostMapping("/appdetails")
	public ResponseEntity<String> addApplicationDetails(@RequestBody Application_Details appDetails) {

		app_DetailsService.addApplicationDetails(appDetails);
		log.info("Accessing post method... New application detail added");

		return ResponseEntity.ok("Application Detail added");
	}

	// Get all application details (TODO)
	@GetMapping("/appdetails")
	public ResponseEntity<List<Application_Details>> getAllApplicationDetails() {
		return null;

	}
}
