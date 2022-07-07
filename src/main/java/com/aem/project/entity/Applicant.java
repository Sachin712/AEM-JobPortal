package com.aem.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "applicant")
public class Applicant {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	// @Column(name = "applicant_id")
	private String id;
	private String applicant_name;
	private String applicant_gender;
	private String applicant_contact_details;
	private String applicant_email_address;
	private String applicant_professional_summary;
	private String applicant_highest_educational_attainment;
	private String applicant_username;
	private String applicant_password;
	private String applicant_account_status;
	private String applicant_profile_image_name;
	private String applicant_profile_image_type;

	@Lob
	private byte[] applicant_profile_image;

	public Applicant(String applicant_name, String applicant_gender, String applicant_contact_details,
			String applicant_email_address, String applicant_professional_summary,
			String applicant_highest_educational_attainment, String applicant_username, String applicant_password,
			String applicant_account_status, String applicant_profile_image_name, String applicant_profile_image_type,
			byte[] applicant_profile_image) {
		this.applicant_name = applicant_name;
		this.applicant_gender = applicant_gender;
		this.applicant_contact_details = applicant_contact_details;
		this.applicant_email_address = applicant_email_address;
		this.applicant_professional_summary = applicant_professional_summary;
		this.applicant_highest_educational_attainment = applicant_highest_educational_attainment;
		this.applicant_username = applicant_username;
		this.applicant_password = applicant_password;
		this.applicant_account_status = applicant_account_status;
		this.applicant_profile_image_name = applicant_profile_image_name;
		this.applicant_profile_image_type = applicant_profile_image_type;
		this.applicant_profile_image = applicant_profile_image;
	}

//	public Applicant(byte[] applicant_profile_image) {
//		this.applicant_profile_image = applicant_profile_image;
//	}

}
