package com.aem.project.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aem.project.entity.Company;
import com.aem.project.repository.CompanyRepository;
import com.aem.project.util.CustomPasswordEncoder;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private CustomPasswordEncoder encoder;

	// Get company by ID
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
	public Company updateCompany(Company companyInfo, Company company) throws IOException {

		companyInfo.setCompany_name(company.getCompany_name());
		companyInfo.setCompany_address(company.getCompany_address());
		companyInfo.setCompany_contact(company.getCompany_contact());
		companyInfo.setCompany_email(company.getCompany_email());
		companyInfo.setCompany_website(company.getCompany_website());
		companyInfo.setUsername(company.getUsername());
		String encodedPassword = encoder.encryptPassword(company.getPassword());
		companyInfo.setPassword(encodedPassword);
		companyInfo.setAccount_status(company.getAccount_status());
		return companyRepo.save(companyInfo);

	}

	// Accessing findById for getting an company info
	public Optional<Company> getCompanyById(String company_id) {
		return companyRepo.findById(company_id);
	}

	// Accessing companydata for adding a company
	public Company addCompany(Company companyData) {
		Company companyInfo = new Company();
		companyInfo.setCompany_name(companyData.getCompany_name());
		companyInfo.setCompany_address(companyData.getCompany_address());
		companyInfo.setCompany_contact(companyData.getCompany_contact());
		companyInfo.setCompany_email(companyData.getCompany_email());
		companyInfo.setCompany_website(companyData.getCompany_website());
		companyInfo.setUsername(companyData.getUsername());
		String encodedPassword = encoder.encryptPassword(companyData.getPassword());
		companyInfo.setPassword(encodedPassword);
		companyInfo.setAccount_status(companyData.getAccount_status());
		return companyRepo.save(companyInfo);

	}

}
