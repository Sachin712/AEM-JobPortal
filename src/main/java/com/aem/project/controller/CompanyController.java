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



import com.aem.project.entity.Company;

import com.aem.project.service.CompanyService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {
	private static final Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private CompanyService companyService;
	
	//To add a new company
	@PostMapping("/companies")
	public ResponseEntity<String> addUser(@RequestBody Company companyData) {
		companyService.addCompany(companyData);
		logger.info("Accessing post method... Company added successfully.");
		return ResponseEntity.ok("Company added!");

	}

	// Get all companies
	@GetMapping("/companies")
	public List<Company> getCompany() {
		return companyService.getAllcompanies();
	}

	// Get companies by Id
	@GetMapping("/companies/{id}")
	public Optional<Company> getCompanies(@PathVariable String id) {
		return companyService.findById(id);
	}

	//Update a company info
	@PutMapping("/companies/{id}")
	public ResponseEntity<String> updateCompany(@PathVariable String id, @RequestBody Company company) {
		Optional<Company> companyData = companyService.findById(id);
		if (companyData.isPresent()) {
			Company companyInfo = companyData.get();
			companyInfo.setCompany_name(company.getCompany_name());
			companyInfo.setCompany_address(company.getCompany_address());
			companyInfo.setCompany_contact(company.getCompany_contact());
			companyInfo.setCompany_email(company.getCompany_email());
			companyInfo.setCompany_website(company.getCompany_website());
			companyInfo.setUsername(company.getUsername());
			companyInfo.setPassword(company.getPassword());			
			companyInfo.setAccount_status(company.getAccount_status());

		companyService.addCompany(companyInfo);
			logger.info("Accessing put method... Company updated successfully.");
			return ResponseEntity.ok("Company Updated!");
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}



}
