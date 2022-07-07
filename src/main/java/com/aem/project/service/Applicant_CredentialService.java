package com.aem.project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aem.project.entity.Applicant_Credential;
import com.aem.project.repository.ApplicantRepository;
import com.aem.project.repository.Applicant_CredentialRepository;

@Service
public class Applicant_CredentialService {

	@Autowired
	private Applicant_CredentialRepository applicant_CredentialRepo;

	@Autowired
	private ApplicantRepository applicantRepo;

	// Add a new credential
	public Applicant_Credential addCredential(String applicantID, String credential_name, MultipartFile file)
			throws IOException {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		Applicant_Credential applicant_Credential = new Applicant_Credential(credential_name, fileName,
				file.getContentType(), file.getBytes());
		Applicant_Credential applicant_Credential2 = applicantRepo.findById(applicantID).map(app -> {
			applicant_Credential.setApplicant(app);
			return applicant_CredentialRepo.save(applicant_Credential);
		}).orElseThrow();
		return applicant_Credential2;

	}

	// Get all credentials
	public List<Applicant_Credential> getAllCredentials(String applicantID) {
		return applicant_CredentialRepo.findByApplicantId(applicantID);
		// TODO Auto-generated method stub

	}

}
