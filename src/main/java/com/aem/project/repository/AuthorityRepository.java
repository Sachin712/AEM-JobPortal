package com.aem.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aem.project.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
