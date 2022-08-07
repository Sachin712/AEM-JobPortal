package com.aem.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.aem.project.entity.Applicant;
import com.aem.project.entity.Company;
import com.aem.project.service.CompanyService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Log4j
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	// To add a new company
	@PostMapping("/company")
	public ResponseEntity<String> addUser(@RequestBody Company companyData,
			@AuthenticationPrincipal Applicant applicant) {

		if (applicant == null)
			return new ResponseEntity<String>("Token null or empty", HttpStatus.UNAUTHORIZED);
		else {
			companyService.addCompany(companyData);
			log.info("Accessing post method... Company added successfully.");
			return ResponseEntity.ok("Company added!");
		}
	}

	// Get all companies
	@GetMapping("/company")
	public List<Company> getCompany() {
		return companyService.getAllcompanies();
	}

	// Get companies by Id
	@GetMapping("/company/{id}")
	public Optional<Company> getCompanies(@PathVariable String id) {
		return companyService.findById(id);
	}

	// Update a company info
	@PutMapping("/company/{id}")
	public ResponseEntity<?> updateCompany(@AuthenticationPrincipal Applicant applicant, @PathVariable String id,
			@RequestBody Company company) throws IOException {

		if (applicant == null)
			return new ResponseEntity<String>("Token null or empty", HttpStatus.UNAUTHORIZED);
		else {

			Optional<Company> companyData = companyService.findById(id);
			if (companyData.isPresent()) {
				Company companyInfo = companyData.get();
				Company updateCompany = companyService.updateCompany(companyInfo, company);
				log.info("Accessing put method... Company updated successfully.");
				return new ResponseEntity<>(updateCompany, HttpStatus.OK);
			} else
				return new ResponseEntity<>("Not found!!", HttpStatus.NOT_FOUND);
		}
	}

}
