package com.aem.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "job")
public class Job {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "job_id")
	private String id;
	private String job_title;
	private String job_description;;
	private String job_location;
	private String job_salary;
	private String job_posting_date;
	private String last_application_date;
	private String no_of_vacancy;
	private String job_status;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_company_id", nullable = false)
	private Company company;

	public Job(String job_title, String job_description, String job_location, String job_salary,
			String job_posting_date, String last_application_date, String no_of_vacancy, String job_status) {
		super();
		this.job_title = job_title;
		this.job_description = job_description;
		this.job_location = job_location;
		this.job_salary = job_salary;
		this.job_posting_date = job_posting_date;
		this.last_application_date = last_application_date;
		this.no_of_vacancy = no_of_vacancy;
		this.job_status = job_status;
	}

}
