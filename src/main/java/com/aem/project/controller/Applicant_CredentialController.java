package com.aem.project.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aem.project.entity.Applicant;
import com.aem.project.entity.Applicant_Credential;
import com.aem.project.service.ApplicantService;
import com.aem.project.service.Applicant_CredentialService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class Applicant_CredentialController {
	private static final Logger logger = Logger.getLogger(ApplicantController.class);

	@Autowired
	private Applicant_CredentialService applicant_CredentialService;
	@Autowired
	private ApplicantService applicationService;

	// Create a new credential
	@PostMapping("/applicants/{applicantID}/credentials")
	public ResponseEntity<String> addCredential(@PathVariable("applicantID") String applicantID,
			@RequestParam("credential_name") String credential_name, @RequestParam(value = "file") MultipartFile file)
			throws IOException {

		Optional<Applicant> applicantData = applicationService.findById(applicantID);
		if (applicantData.isPresent()) {
			applicant_CredentialService.addCredential(applicantID, credential_name, file);
			logger.info("Accessing post method... New Credenital created");

			return ResponseEntity.ok("Credenital Added!");
		} else {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return ResponseEntity.ok("Applicant not found");
		}

	}

	// Get all credentials
	@GetMapping("/applicants/{applicantID}/credentials")
	public ResponseEntity<List<Applicant_Credential>> getAllCredentials(
			@PathVariable("applicantID") String applicantID) {

		Optional<Applicant> applicantData = applicationService.findById(applicantID);
		if (applicantData.isPresent()) {
			List<Applicant_Credential> applicant_Credentials = applicant_CredentialService
					.getAllCredentials(applicantID);
			logger.info("Accessing get method... Credenitals found");

			return new ResponseEntity<>(applicant_Credentials, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}
}
