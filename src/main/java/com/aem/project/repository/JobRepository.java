package com.aem.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aem.project.entity.Job;

public interface JobRepository extends JpaRepository<Job, String> {
	List<Job> findByCompanyId(String companyID);

}
