package com.aem.project.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aem.project.entity.Applicant;
import com.aem.project.entity.Applicant_Credential;
import com.aem.project.repository.ApplicantRepository;
import com.aem.project.repository.Applicant_CredentialRepository;

@Service
public class Applicant_CredentialService {

	@Autowired
	private Applicant_CredentialRepository applicant_CredentialRepo;

	@Autowired
	private ApplicantRepository applicantRepo;

	private String docURL = "xx";

	// Add a new credential
	public Applicant_Credential addCredential(String applicantID, MultipartFile file) throws IOException {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		Applicant_Credential applicant_Credential = new Applicant_Credential(fileName, docURL, file.getContentType(),
				file.getBytes());

		Applicant_Credential applicant_Credential2 = applicantRepo.findById(applicantID).map(app -> {
			applicant_Credential.setApplicant(app);
			return applicant_CredentialRepo.save(applicant_Credential);
		}).orElseThrow();
		return applicant_Credential2;

	}

	// Get all credentials for an applicant
	public List<Applicant_Credential> getAllCredentials(String applicantID) {
		return applicant_CredentialRepo.findByApplicantId(applicantID);
	}

	public Applicant_Credential getCredentialById(String id) {
		return applicant_CredentialRepo.findById(id).get();
	}

	public Applicant_Credential updateCredential(Applicant_Credential credentialInfo, MultipartFile file)
			throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		credentialInfo.setCredential_name(fileName);
		credentialInfo.setDocument(docURL);
		credentialInfo.setCredential_file_type(file.getContentType());
		credentialInfo.setFile_upload(file.getBytes());

		return applicant_CredentialRepo.save(credentialInfo);

	}

	public void deleteCredential(String id) {
		// TODO Auto-generated method stub
		applicant_CredentialRepo.deleteById(id);
		return;

	}

	public Applicant_Credential setDoc(Applicant_Credential appCred) {
		// TODO Auto-generated method stub
		return applicant_CredentialRepo.save(appCred);
	}

}
