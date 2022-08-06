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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aem.project.entity.Applicant;
import com.aem.project.entity.Applicant_Credential;
import com.aem.project.entity.ResponseFile;
import com.aem.project.service.ApplicantService;
import com.aem.project.service.Applicant_CredentialService;

import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Log4j
public class Applicant_CredentialController {

	@Autowired
	private Applicant_CredentialService applicant_CredentialService;
	@Autowired
	private ApplicantService applicationService;

	private ResponseFile resFile;

	// Create a new credential
	@PostMapping("/applicants/{applicantID}/credentials")
	public ResponseFile addCredential(@PathVariable("applicantID") String applicantID,
			@RequestParam("credential_name") String credential_name, @RequestParam(value = "file") MultipartFile file,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws IOException {

		String tempToken = token.split(" ")[0].trim();
		if (!token.isEmpty() || !token.equals(null) || tempToken.equals("Bearer")) {

			Optional<Applicant> applicantData = applicationService.findById(applicantID);
			if (applicantData.isPresent()) {
				Applicant_Credential appCred = applicant_CredentialService.addCredential(applicantID, credential_name,
						file);

				String fileName = ServletUriComponentsBuilder.fromCurrentContextPath().path("/doc/")
						.path(appCred.getId()).toUriString();
				appCred.setDocument(fileName);

				applicant_CredentialService.setDoc(appCred);

				log.info("Accessing post method... New Credenital created");

				resFile = new ResponseFile(appCred.getCredential_name(), fileName, appCred.getCredential_file_type(),
						appCred.getFile_upload().length);
				// setDocName(appCred);

				return resFile;
				// new ResponseEntity<String>("done", HttpStatus.OK);

			}
		}
		return null;
	}

	public void setDocName(Applicant_Credential appCred) {
		String docName = resFile.getUrl();
		Optional<Applicant_Credential> credInfo = Optional
				.of(applicant_CredentialService.getCredentialById(appCred.getId()));
		if (credInfo.isPresent()) {
			appCred.setDocument(docName);

		} else
			return;
	}

	// Get document download link
	@GetMapping("/doc/{credId}")
	public ResponseEntity<?> downloadFile(@PathVariable String credId) {
		// Load file from database

		Applicant_Credential app_Credential = applicant_CredentialService.getCredentialById(credId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(app_Credential.getCredential_file_type()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + app_Credential.getDocument() + "\"")
				.body(new ByteArrayResource(app_Credential.getFile_upload()));
//		} else
//			return new ResponseEntity<>("No document found!!", HttpStatus.NOT_FOUND);
	}

	// Get all credentials
	@GetMapping("/applicants/{applicantID}/credentials")
	public ResponseEntity<List<Applicant_Credential>> getAllCredentials(
			@PathVariable("applicantID") String applicantID) {

		Optional<Applicant> applicantData = applicationService.findById(applicantID);
		if (applicantData.isPresent()) {
			List<Applicant_Credential> applicant_Credentials = applicant_CredentialService
					.getAllCredentials(applicantID);
			log.info("Accessing get method... Credenitals found");

			return new ResponseEntity<>(applicant_Credentials, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	// Get credential by Id
	@GetMapping("/credentials/{id}")
	public Applicant_Credential getCredential(@PathVariable("id") String id) {

		return applicant_CredentialService.getCredentialById(id);
	}

	// Update a credential
	@PutMapping("/credentials/{id}")
	public ResponseEntity<String> updateCredential(@PathVariable("id") String id,
			@RequestParam("credential_name") String credential_name, @RequestParam(value = "file") MultipartFile file,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		String tempToken = token.split(" ")[0].trim();
		if (token.isEmpty() || token.equals(null) || !tempToken.equals("Bearer"))
			return new ResponseEntity<String>("Token null or empty", HttpStatus.UNAUTHORIZED);
		else {

			Optional<Applicant_Credential> credentialData = Optional
					.of(applicant_CredentialService.getCredentialById(id));
			if (credentialData.isPresent()) {
				Applicant_Credential credentialInfo = credentialData.get();
				try {
					applicant_CredentialService.updateCredential(credentialData, credentialInfo, credential_name, file);
					log.info("Accessing put method... Credential updated successully.");
					return ResponseEntity.ok("Credential Updated");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				new ResponseEntity<>(HttpStatus.NOT_FOUND);
				return ResponseEntity.ok("Credential not found.");
			}
		}
	}

	@DeleteMapping("/admins/{id}")
	public ResponseEntity<?> deleteCredential(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
			@PathVariable String id) {
		String tempToken = token.split(" ")[0].trim();
		if (token.isEmpty() || token.equals(null) || !tempToken.equals("Bearer"))
			return new ResponseEntity<String>("Token null or empty", HttpStatus.UNAUTHORIZED);
		else {

			Optional<Applicant_Credential> credentialInfo = Optional
					.of(applicant_CredentialService.getCredentialById(id));
			if (credentialInfo.isPresent()) {
				applicant_CredentialService.deleteCredential(id);
				log.info("Accessing delete method... Credential deleted successully.");
				return new ResponseEntity<String>("Credential Deleted!", HttpStatus.OK);
			} else
				return new ResponseEntity<String>("No Credential found!", HttpStatus.NOT_FOUND);

		}
	}
}
