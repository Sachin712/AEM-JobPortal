package com.aem.project.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aem.project.entity.Applicant;
import com.aem.project.repository.ApplicantRepository;

@Service
public class ApplicantService {

	@Autowired
	private ApplicantRepository applicantRepo;

	// Get applicant by ID
	public Applicant getApplicant(String id) {
		return applicantRepo.findById(id).orElseThrow();
	}

	// Get all applicants
	public List<Applicant> getAllApplicants() {
		return applicantRepo.findAll();
	}

	// Add a new applicant
	public Applicant addApplicant(String applicant_name, String applicant_gender, String applicant_contact_details,
			String applicant_email_address, String applicant_professional_summary,
			String applicant_highest_educational_attainment, String applicant_username, String applicant_password,
			String applicant_account_status, MultipartFile file) throws IOException {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Applicant applicantData = new Applicant(applicant_name, applicant_gender, applicant_contact_details,
				applicant_email_address, applicant_professional_summary, applicant_highest_educational_attainment,
				applicant_username, applicant_password, applicant_account_status, fileName, file.getContentType(),
				file.getBytes());
		return applicantRepo.save(applicantData);
	}

	// Accessing findApplicantById for updating applicant
	public Optional<Applicant> findById(String applicant_ID) {
		return applicantRepo.findById(applicant_ID);
	}

	// Updating an existing applicant
	public Applicant updateApplicant(Optional<Applicant> applicantData, Applicant applicantInfo, String applicant_name,
			String applicant_gender, String applicant_contact_details, String applicant_email_address,
			String applicant_professional_summary, String applicant_highest_educational_attainment,
			String applicant_username, String applicant_password, String applicant_account_status, MultipartFile file)
			throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		applicantInfo.setApplicant_name(applicant_name);
		applicantInfo.setApplicant_gender(applicant_gender);
		applicantInfo.setApplicant_contact_details(applicant_contact_details);
		applicantInfo.setApplicant_email_address(applicant_email_address);
		applicantInfo.setApplicant_professional_summary(applicant_professional_summary);
		applicantInfo.setApplicant_highest_educational_attainment(applicant_highest_educational_attainment);
		applicantInfo.setApplicant_username(applicant_username);
		applicantInfo.setApplicant_password(applicant_password);
		applicantInfo.setApplicant_account_status(applicant_account_status);
		applicantInfo.setApplicant_profile_image_name(fileName);
		applicantInfo.setApplicant_profile_image_type(file.getContentType());
		applicantInfo.setApplicant_profile_image(file.getBytes());

		return applicantRepo.save(applicantInfo);

	}

	public Optional<Applicant> getApplicantById(String id) {
		return applicantRepo.findById(id);
	}

}
