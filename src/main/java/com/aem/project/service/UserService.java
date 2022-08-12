package com.aem.project.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aem.project.entity.User;
import com.aem.project.repository.UserRepository;
import com.aem.project.util.CustomPasswordEncoder;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CustomPasswordEncoder encoder;

	// Get applicant by ID
	public User getUser(String id) {
		return userRepo.findById(id).orElseThrow();
	}

	// Get all applicants
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	// Add a new applicant
	public User addUser(String name, String gender, String contact_details, String email_address,
			String professional_summary, String highest_educational_attainment, String username, String password,
			MultipartFile file, int isAdmin) throws IOException {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String encodedPassword = encoder.encryptPassword(password);
		User applicantData = new User(name, gender, contact_details, email_address, professional_summary,
				highest_educational_attainment, username, encodedPassword, fileName, file.getContentType(),
				file.getBytes(), isAdmin);
		return userRepo.save(applicantData);
	}

	// Accessing findApplicantById for updating applicant
	public Optional<User> findById(String ID) {
		return userRepo.findById(ID);
	}

	// Updating an existing applicant
	public User updateUser(Optional<User> applicantData, User applicantInfo, String name, String gender,
			String contact_details, String email_address, String professional_summary,
			String highest_educational_attainment, String username, String password, MultipartFile file,
			int isAdmin) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		applicantInfo.setName(name);
		applicantInfo.setGender(gender);
		applicantInfo.setContact_details(contact_details);
		applicantInfo.setEmail_address(email_address);
		applicantInfo.setProfessional_summary(professional_summary);
		applicantInfo.setHighest_educational_attainment(highest_educational_attainment);
		applicantInfo.setUsername(username);
		applicantInfo.setPassword(password);
		applicantInfo.setProfile_image_name(fileName);
		applicantInfo.setProfile_image_type(file.getContentType());
		applicantInfo.setProfile_image(file.getBytes());
		applicantInfo.setIsAdmin(isAdmin);

		return userRepo.save(applicantInfo);

	}

}
