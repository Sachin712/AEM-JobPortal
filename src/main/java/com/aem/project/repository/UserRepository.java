package com.aem.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aem.project.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUsername(String username);

}
