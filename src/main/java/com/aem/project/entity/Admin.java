package com.aem.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "admin")
public class Admin {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "admin_id")
	private String id;
	private String admin_name;
	private String admin_contact;
	private String admin_email;
	private String admin_username;
	private String admin_password;
	
	
	public Admin(String admin_name, String admin_contact, String admin_email, String admin_username,
			String admin_password) {
	
		this.admin_name = admin_name;
		this.admin_contact = admin_contact;
		this.admin_email = admin_email;
		this.admin_username = admin_username;
		this.admin_password = admin_password;
	}


}


	