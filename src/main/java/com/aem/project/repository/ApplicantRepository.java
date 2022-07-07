package com.aem.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aem.project.entity.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, String> {

}
