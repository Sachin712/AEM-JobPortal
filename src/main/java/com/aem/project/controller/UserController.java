package com.aem.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aem.project.entity.User;
import com.aem.project.entity.ResponseFile;
import com.aem.project.service.UserService;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Log4j
@Api(value = "User", description = "Users and Admin data")
public class UserController {
	@Autowired
	private UserService userService;

	private int isAdmin = 0;

	// Create a new applicant
	@PostMapping("/users")
	public ResponseFile addUsers(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "contact_details", required = false) String contact_details,
			@RequestParam(value = "email_address", required = false) String email_address,
			@RequestParam(value = "professional_summary", required = false) String professional_summary,
			@RequestParam(value = "highest_educational_attainment", required = false) String highest_educational_attainment,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "file") MultipartFile file) throws IOException {

		User applicant = userService.addUser(name, gender, contact_details, email_address, professional_summary,
				highest_educational_attainment, username, password, file, isAdmin);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/profile/")
				.path(applicant.getId()).toUriString();

		log.info("Accessing post method... New applicant created");
		return new ResponseFile(applicant.getProfile_image_name(), fileDownloadUri, applicant.getProfile_image_type(),
				applicant.getProfile_image().length);

	}

	// Get applicants DP by Id
	@GetMapping("/users/profile/{profileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String profileId) {
		// Load file from database

		User applicant = userService.getUser(profileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(applicant.getProfile_image_type()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + applicant.getProfile_image_name() + "\"")
				.body(new ByteArrayResource(applicant.getProfile_image()));
	}

	// Get all applicants
	@GetMapping("/users")
	public List<User> getApplicants() {
		return userService.getAllUsers();
	}

	// Get applicant by Id
	@GetMapping("/users/{id}")
	public Optional<User> getApplicants(@PathVariable String id) {
		return userService.findById(id);
	}

	// Update an existing applicant
	@PutMapping("/users/{id}")
	public ResponseEntity<String> updateApplicant(@PathVariable String id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "contact_details", required = false) String contact_details,
			@RequestParam(value = "email_address", required = false) String email_address,
			@RequestParam(value = "professional_summary", required = false) String professional_summary,
			@RequestParam(value = "highest_educational_attainment", required = false) String highest_educational_attainment,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "file") MultipartFile file, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		String tempToken = token.split(" ")[0].trim();
		if (token.isEmpty() || token.equals(null) || !tempToken.equals("Bearer"))
			return new ResponseEntity<String>("Token null or empty", HttpStatus.UNAUTHORIZED);
		else {

			Optional<User> applicantData = userService.findById(id);
			if (applicantData.isPresent()) {
				User applicantInfo = applicantData.get();
				try {

					userService.updateUser(applicantData, applicantInfo, name, gender, contact_details, email_address,
							professional_summary, highest_educational_attainment, username, password, file, isAdmin);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				log.info("Accessing put method... User updated successfully.");
				return ResponseEntity.ok("User Updated!");

			} else {
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
				return ResponseEntity.ok("User not found");
			}

		}
	}

}
