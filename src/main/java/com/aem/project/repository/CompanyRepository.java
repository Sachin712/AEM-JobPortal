package com.aem.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.aem.project.entity.Company;


public interface CompanyRepository extends JpaRepository<Company, String> {

}
