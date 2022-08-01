package com.aem.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aem.project.entity.Applicant;
import com.aem.project.entity.ResponseFile;
import com.aem.project.service.ApplicantService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicantController {
	private static final Logger logger = Logger.getLogger(ApplicantController.class);

	@Autowired
	private ApplicantService applicationService;

	// Create a new applicant
	@PostMapping("/applicants")
	public ResponseFile addApplicants(@RequestParam(value = "applicant_name", required = false) String applicant_name,
			@RequestParam(value = "applicant_gender", required = false) String applicant_gender,
			@RequestParam(value = "applicant_contact_details", required = false) String applicant_contact_details,
			@RequestParam(value = "applicant_email_address", required = false) String applicant_email_address,
			@RequestParam(value = "applicant_professional_summary", required = false) String applicant_professional_summary,
			@RequestParam(value = "applicant_highest_educational_attainment", required = false) String applicant_highest_educational_attainment,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "applicant_password", required = false) String applicant_password,
			@RequestParam(value = "applicant_account_status", required = false) String applicant_account_status,
			@RequestParam(value = "file") MultipartFile file) throws IOException {
		Applicant applicant = applicationService.addApplicant(applicant_name, applicant_gender,
				applicant_contact_details, applicant_email_address, applicant_professional_summary,
				applicant_highest_educational_attainment, username, applicant_password, applicant_account_status, file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/profile/")
				.path(applicant.getId()).toUriString();

		logger.info("Accessing put method... New applicant created");
		return new ResponseFile(applicant.getApplicant_profile_image_name(), fileDownloadUri,
				applicant.getApplicant_profile_image_type(), applicant.getApplicant_profile_image().length);

	}

	// Get applicants DP by Id
	@GetMapping("/applicants/profile/{profileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String profileId) {
		// Load file from database

		Applicant applicant = applicationService.getApplicant(profileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(applicant.getApplicant_profile_image_type()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + applicant.getApplicant_profile_image_name() + "\"")
				.body(new ByteArrayResource(applicant.getApplicant_profile_image()));
	}

	// Get all applicants
	@GetMapping("/applicants")
	public List<Applicant> getApplicants() {
		return applicationService.getAllApplicants();
	}

	// Get applicant by Id
	@GetMapping("/applicants/{id}")
	public Optional<Applicant> getApplicants(@PathVariable String id) {
		return applicationService.getApplicantById(id);
	}

	// Update an existing applicant
	@PutMapping("/applicants/{id}")
	public ResponseEntity<String> updateApplicant(@PathVariable String id,
			@RequestParam(value = "applicant_name", required = false) String applicant_name,
			@RequestParam(value = "applicant_gender", required = false) String applicant_gender,
			@RequestParam(value = "applicant_contact_details", required = false) String applicant_contact_details,
			@RequestParam(value = "applicant_email_address", required = false) String applicant_email_address,
			@RequestParam(value = "applicant_professional_summary", required = false) String applicant_professional_summary,
			@RequestParam(value = "applicant_highest_educational_attainment", required = false) String applicant_highest_educational_attainment,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "applicant_password", required = false) String applicant_password,
			@RequestParam(value = "applicant_account_status", required = false) String applicant_account_status,
			@RequestParam(value = "file") MultipartFile file) throws IOException {

		Optional<Applicant> applicantData = applicationService.findById(id);
		if (applicantData.isPresent()) {
			Applicant applicantInfo = applicantData.get();
			applicationService.updateApplicant(applicantData, applicantInfo, applicant_name, applicant_gender,
					applicant_contact_details, applicant_email_address, applicant_professional_summary,
					applicant_highest_educational_attainment, username, applicant_password, applicant_account_status,
					file);
			logger.info("Accessing put method... Applicant updated successfully.");
			return ResponseEntity.ok("Applicant Updated!");

		} else {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return ResponseEntity.ok("Applicant not found");
		}

	}

}
