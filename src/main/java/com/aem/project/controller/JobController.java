package com.aem.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

import com.aem.project.entity.User;
import com.aem.project.entity.Company;
import com.aem.project.entity.Job;
import com.aem.project.service.CompanyService;
import com.aem.project.service.JobService;

import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Log4j
public class JobController {
	@Autowired
	private JobService jobService;

	@Autowired
	private CompanyService companyService;

	// Create a new Job
	@PostMapping("/company/{companyID}/jobs")
	public ResponseEntity<String> addJob(@AuthenticationPrincipal User applicant,
			@PathVariable("companyID") String companyID, @RequestBody Job job) {

		if (applicant == null)
			return new ResponseEntity<String>("Token null or empty", HttpStatus.UNAUTHORIZED);
		else {

			Optional<Company> companyData = companyService.findById(companyID);
			if (companyData.isPresent()) {
				jobService.addJob(companyID, job);
				log.info("Accessing post method... New Job created");
				return ResponseEntity.ok("Job Added!");
			} else {
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
				return ResponseEntity.ok("Job not found");
			}
		}
	}

	// Get all jobs for a companyID
	@GetMapping("/company/{companyID}/jobs")
	public ResponseEntity<List<Job>> getAllJobs(@PathVariable("companyID") String companyID) {

		Optional<Company> companyData = companyService.findById(companyID);
		if (companyData.isPresent()) {
			List<Job> job = jobService.getAllJobs(companyID);
			return new ResponseEntity<>(job, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	// Get a job by ID
	@GetMapping("/job/{id}")
	public Optional<Job> findJobById(@PathVariable("id") String id) {
		return jobService.findJobById(id);
	}

	// Get all jobs
	@GetMapping("/jobs")
	public List<Job> getJobs() {
		return jobService.getJobs();
	}

	// Update a job
	@PutMapping("/job/{id}")
	public ResponseEntity<String> updateJob(@AuthenticationPrincipal User applicant, @PathVariable("id") String id,
			@RequestBody Job job) {

		if (applicant == null)
			return new ResponseEntity<String>("Token null or empty", HttpStatus.UNAUTHORIZED);
		else {

			Optional<Job> jobData = jobService.findJobById(id);
			if (jobData.isPresent()) {
				jobService.updateJob(jobData, job);
				log.info("Accessing put method... Job updated");
				return ResponseEntity.ok("Job Updated!");
			} else {
				return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
			}
		}

	}

	// Delete a job
	@DeleteMapping("/job/{id}")
	public ResponseEntity<String> deleteJob(@AuthenticationPrincipal User applicant,
			@PathVariable("id") String id) {
		if (applicant == null)
			return new ResponseEntity<String>("Token null or empty", HttpStatus.UNAUTHORIZED);
		else {

			Optional<Job> jobData = jobService.findJobById(id);
			if (jobData.isPresent()) {
				jobService.deleteJob(id);
				log.info("Accessing delete method... Job deleted");
				return ResponseEntity.ok("Job Deleted!");
			} else {
				return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
			}
		}
	}
}
