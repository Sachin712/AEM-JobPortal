package com.aem.project.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aem.project.entity.Admin;
import com.aem.project.entity.Company;
import com.aem.project.repository.AdminRepository;
import com.aem.project.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepo;

	// Get company  by ID
	public Company getCompany(String company_id) {
		return companyRepo.findById(company_id).orElseThrow();
	}

	// Get all companies
	public List<Company> getAllcompanies() {
		return companyRepo.findAll();
	}

	// Accessing findCompanyByID for updating company
	public Optional<Company> findById(String company_id) {
		return companyRepo.findById(company_id);
	}

	// Updating an existing company
	public Company updateCompany(Optional<Company> companyData, Company companyInfo, String company_name, String company_address, String company_contact, String company_email,String company_website, String username, String password, String account_status)
			throws IOException {

		companyInfo.setCompany_name(company_name);
		companyInfo.setCompany_address(company_address);
		companyInfo.setCompany_contact(company_contact);
		companyInfo.setCompany_email(company_email);
		companyInfo.setCompany_website(company_website);
		companyInfo.setUsername(username);
		companyInfo.setPassword(password);
		companyInfo.setAccount_status(account_status);
		
		return companyRepo.save(companyInfo);

	}
	// Accessing findById for getting an company info
	public Optional<Company> getCompanyById(String company_id) {
		return companyRepo.findById(company_id);
	}
	// Accessing companydata for adding a company
	public Company addCompany(Company companyData) {
		return companyRepo.save(companyData);
		
	}



}
