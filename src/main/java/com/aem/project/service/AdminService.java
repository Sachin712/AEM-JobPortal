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
	public Admin updateAdmin(Admin adminInfo, Admin admin) throws IOException {

		adminInfo.setAdmin_name(admin.getAdmin_name());
		adminInfo.setAdmin_contact(admin.getAdmin_contact());
		adminInfo.setAdmin_email(admin.getAdmin_email());
		adminInfo.setAdmin_password(admin.getAdmin_password());
		adminInfo.setAdmin_username(admin.getAdmin_username());
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
