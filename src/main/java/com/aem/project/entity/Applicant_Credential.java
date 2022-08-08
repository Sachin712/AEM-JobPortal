package com.aem.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "applicant_credential")
public class Applicant_Credential {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "credential_id")
	private String id;
	private String credential_name;

	private String document;
	private String credential_file_type;
	@Lob
	private byte[] file_upload;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_user_id", nullable = false)
	private User user;

	public Applicant_Credential(String credential_name, String document, String credential_file_type,
			byte[] file_upload) {
		this.credential_name = credential_name;
		this.document = document;
		this.credential_file_type = credential_file_type;
		this.file_upload = file_upload;
	}
}
