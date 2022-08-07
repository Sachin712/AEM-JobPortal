package com.aem.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aem.project.entity.Application_Details;

public interface Application_DetailsRepository extends JpaRepository<Application_Details, String> {
	List<Application_Details> findByApplicantId(String applicantID);
}
