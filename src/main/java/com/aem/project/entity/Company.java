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
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "company_id")
	private String id;
	private String company_name;
	private String company_address;
	private String company_contact;
	private String company_email;
	private String company_website;
	private String username;
	private String password;
	private String account_status;

	public Company(String company_name, String company_address, String company_contact, String company_email,
			String company_website, String username, String password, String account_status) {
		super();
		this.company_name = company_name;
		this.company_address = company_address;
		this.company_contact = company_contact;
		this.company_email = company_email;
		this.company_website = company_website;
		this.username = username;
		this.password = password;
		this.account_status = account_status;
	}
}
