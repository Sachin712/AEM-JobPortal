package com.aem.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aem.project.entity.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, String> {

	Optional<Applicant> findByUsername(String username);

}
