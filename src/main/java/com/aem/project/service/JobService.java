package com.aem.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aem.project.entity.Job;
import com.aem.project.repository.CompanyRepository;
import com.aem.project.repository.JobRepository;

@Service
public class JobService {
	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private CompanyRepository companyRepository;

	public Job addJob(String companyID, Job job) {
		Job jobData = new Job(job.getJob_title(), job.getJob_description(), job.getJob_location(), job.getJob_salary(),
				job.getJob_posting_date(), job.getLast_application_date(), job.getNo_of_vacancy(), job.getJob_status());

		Job jobData2 = companyRepository.findById(companyID).map(company -> {
			jobData.setCompany(company);
			return jobRepository.save(jobData);
		}).orElseThrow();

		return jobData2;
		// TODO Auto-generated method stub

	}

	public List<Job> getAllJobs(String companyID) {
		// TODO Auto-generated method stub

		return jobRepository.findByCompanyId(companyID);
	}

	public Optional<Job> findJobById(String jobID) {
		// TODO Auto-generated method stub
		return jobRepository.findById(jobID);
	}

	public Job updateJob(Optional<Job> jobData, Job job) {
		// TODO Auto-generated method stub
		Job jobInfo = jobData.get();
		jobInfo.setJob_title(job.getJob_title());
		jobInfo.setJob_description(job.getJob_description());
		jobInfo.setJob_location(job.getJob_location());
		jobInfo.setJob_salary(job.getJob_salary());
		jobInfo.setJob_posting_date(job.getJob_posting_date());
		jobInfo.setLast_application_date(job.getLast_application_date());
		jobInfo.setNo_of_vacancy(job.getNo_of_vacancy());
		jobInfo.setJob_status(job.getJob_status());
		return jobRepository.save(jobInfo);
	}

	public void deleteJob(String id) {
		// TODO Auto-generated method stub
		jobRepository.deleteById(id);
		return;
	}

	public List<Job> getJobs() {
		// TODO Auto-generated method stub
		return  jobRepository.findAll();
	}

}
