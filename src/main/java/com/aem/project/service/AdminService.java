package com.aem.project.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aem.project.entity.Admin;
import com.aem.project.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepo;

	// Get admin by ID
	public Admin getAdmin(String admin_id) {
		return adminRepo.findById(admin_id).orElseThrow();
	}

	// Get all admins
	public List<Admin> getAllAdmins() {
		return adminRepo.findAll();
	}

	// Accessing findAdminById for updating admin
	public Optional<Admin> findById(String admin_id) {
		return adminRepo.findById(admin_id);
	}

	// Updating an existing admin
	public Admin updateAdmin(Optional<Admin> adminData, Admin adminInfo, String admin_name, String admin_contact,
			String admin_email, String admin_username, String admin_password) throws IOException {

		adminInfo.setAdmin_name(admin_name);
		adminInfo.setAdmin_contact(admin_contact);
		adminInfo.setAdmin_email(admin_email);
		adminInfo.setAdmin_username(admin_username);
		adminInfo.setAdmin_password(admin_password);

		return adminRepo.save(adminInfo);
	}

	// Accessing findById for getting an admin
	public Optional<Admin> getApplicantById(String admin_id) {
		return adminRepo.findById(admin_id);
	}

	// Accessing admindata for adding an admin
	public Admin addAdmin(Admin adminData) {
		return adminRepo.save(adminData);

	}

}
