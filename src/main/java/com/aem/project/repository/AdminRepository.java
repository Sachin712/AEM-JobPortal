package com.aem.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aem.project.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin, String> {

}
