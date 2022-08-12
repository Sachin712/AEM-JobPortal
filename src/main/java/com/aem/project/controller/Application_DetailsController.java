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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aem.project.entity.User;
import com.aem.project.entity.Applicant_Credential;
import com.aem.project.entity.Application_Details;
import com.aem.project.entity.Job;
import com.aem.project.service.UserService;
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
	UserService appService;

	@Autowired
	JobService jobService;

	@Autowired
	private UserService applicationService;

	// Add a new application detail
	@PostMapping("/appdetails/{app_id}/{job_id}")
	public ResponseEntity<?> addApplicationDetails(@PathVariable String app_id, @PathVariable String job_id,
			@RequestBody Application_Details application_Details) {

		Optional<User> appInfo = appService.findById(app_id);
		Optional<Job> jobInfo = jobService.findJobById(job_id);

		if (appInfo.isPresent() && jobInfo.isPresent()) {
			User appData = appInfo.get();
			appData.setId(app_id);
			Job jobData = jobInfo.get();
			jobData.setId(job_id);

			Application_Details appDetails = app_DetailsService.addApplicationDetails(appData, jobData,
					application_Details);

			log.info("Accessing post method... New application detail added");
			return new ResponseEntity<>(appDetails, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Application Detail not found", HttpStatus.NOT_FOUND);

		}
	}

	// Get all application details by Id
	@GetMapping("/appdetails/details/{appDetail_Id}")
	public Application_Details getApplicationDetailsById(@PathVariable String appDetail_Id) {

		return app_DetailsService.getApplicationDetailsById(appDetail_Id);
	}

	// Get all application details
	@GetMapping("/appdetails")
	public List<Application_Details> getAllApplicationDetails() {
		return app_DetailsService.getApplicationDetails();
	}

	// Get all application details for a specific job
	@GetMapping("/appdetails/{jobId}")
	public ResponseEntity<?> getAllApplicationDetailsById(@PathVariable String jobId) {

		Optional<Job> jobDataData = jobService.findJobById(jobId);
 
		if (jobDataData.isPresent()) {
			List<Application_Details> appDetails = app_DetailsService.getAllApplications(jobId);

			return new ResponseEntity<>(appDetails, HttpStatus.OK);
		} else
			return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
	}

	// Get all application details for a specific applicant
	@GetMapping("/appdetails/appId/{appId}")
	public ResponseEntity<?> getAllAppDetailsById(@PathVariable String appId) {

		Optional<User> userData = appService.findById(appId);

		if (userData.isPresent()) {
			List<Application_Details> appDetails = app_DetailsService.findByUserId(appId);

			return new ResponseEntity<>(appDetails, HttpStatus.OK);
		} else
			return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
	}

	// Update application detail
	@PutMapping("/appdetails/{appDetail_id}")
	public ResponseEntity<?> updateApplicationDetails(@PathVariable String appDetail_id,
			@RequestBody Application_Details application_Details) {

		Optional<Application_Details> appDeets = Optional
				.of(app_DetailsService.getApplicationDetailsById(appDetail_id));

		if (appDeets.isPresent()) {

			Application_Details appD = appDeets.get();

			Application_Details appDetails = app_DetailsService.updateApplicationDetails(appD, application_Details);

			log.info("Accessing put method... Application details updated");
			return new ResponseEntity<>(appDetails, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Application Details not updated", HttpStatus.NOT_MODIFIED);

		}
	}
}
