package com.aem.project.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7753473446454544865L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	// @Column(name = "id")
	private String id;
	private String name;
	private String gender;
	private String contact_details;
	private String email_address;
	private String professional_summary;
	private String highest_educational_attainment;
	private String username;
	private String password;
	private String profile_image_name;
	private String profile_image_type;
	private int isAdmin;

	@Lob
	private byte[] profile_image;

	public User(String name, String gender, String contact_details, String email_address, String professional_summary,
			String highest_educational_attainment, String username, String password, String profile_image_name,
			String profile_image_type, byte[] profile_image, int isAdmin) {
		this.name = name;
		this.gender = gender;
		this.contact_details = contact_details;
		this.email_address = email_address;
		this.professional_summary = professional_summary;
		this.highest_educational_attainment = highest_educational_attainment;
		this.username = username;
		this.password = password;
		this.profile_image_name = profile_image_name;
		this.profile_image_type = profile_image_type;
		this.profile_image = profile_image;
		this.isAdmin = isAdmin;
	}

	public User(String name, String username, String password, String email_address, String contact_details) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email_address = email_address;
		this.contact_details = contact_details;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
