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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aem.project.entity.Applicant;
import com.aem.project.entity.Application_Details;
import com.aem.project.entity.Job;
import com.aem.project.service.ApplicantService;
import com.aem.project.service.Application_DetailsService;
import com.aem.project.service.JobService;

import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Log4j
public class Application_DetailsController {
	@Autowired
	Application_DetailsService app_DetailsService;

	@Autowired
	ApplicantService appService;

	@Autowired
	JobService jobService;

	// Add a new application detail
	@PostMapping("/appdetails/{app_id}/{job_id}")
	public ResponseEntity<?> addApplicationDetails(@PathVariable String app_id, @PathVariable String job_id,
			@RequestBody Application_Details application_Details) {

		Optional<Applicant> appInfo = appService.findById(app_id);
		Optional<Job> jobInfo = jobService.findJobById(job_id);

		if (appInfo.isPresent() && jobInfo.isPresent()) {
			Applicant appData = appInfo.get();
			appData.setId(app_id);
			Job jobData = jobInfo.get();
			jobData.setId(job_id);

			Application_Details appDetails = app_DetailsService.addApplicationDetails(appData, jobData,
					application_Details);

			return new ResponseEntity<>(appDetails, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Application Detail not found", HttpStatus.NOT_FOUND);

		}
		// app_DetailsService.addApplicationDetails(appDetails);
		// log.info("Accessing post method... New application detail added");
	}

	// Get all application details (TODO)
	@GetMapping("/appdetails")
	public ResponseEntity<List<Application_Details>> getAllApplicationDetails() {
		return null;

	}
}
