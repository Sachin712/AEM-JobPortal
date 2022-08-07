package com.aem.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aem.project.entity.Applicant_Credential;

public interface Applicant_CredentialRepository extends JpaRepository<Applicant_Credential, String> {
	List<Applicant_Credential> findByApplicantId(String applicantID);
}
