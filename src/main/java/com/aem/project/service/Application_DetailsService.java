package com.aem.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aem.project.entity.User;
import com.aem.project.entity.Application_Details;
import com.aem.project.entity.Job;
import com.aem.project.repository.Application_DetailsRepository;

@Service
public class Application_DetailsService {

	@Autowired
	Application_DetailsRepository app_DetailsRepository;

	// Add an application detail
	public Application_Details addApplicationDetails(User appData2, Job jobData2, Application_Details appDetails) {
		// TODO Auto-generated method stub

		Application_Details appD = new Application_Details();
		appD.setUser(appData2);
		appD.setJob(jobData2);
		appD.setApplication_status(appDetails.getApplication_status());
		appD.setApplication_doc(appDetails.getApplication_doc());

		return app_DetailsRepository.save(appD);
	}

	// Get all application details
	public List<Application_Details> getApplicationDetails() {
		// TODO Auto-generated method stub
		return app_DetailsRepository.findAll();
	}

	// Get an application detail by job Id
	public List<Application_Details> getAllApplications(String jobId) {
		// TODO Auto-generated method stub
		return app_DetailsRepository.findByJobId(jobId);
	}

	// Get an application detail by Id
	public Application_Details getApplicationDetailsById(String appDetail_Id) {
		// TODO Auto-generated method stub
		return app_DetailsRepository.findById(appDetail_Id).get();
	}

	// Update an application detail
	public Application_Details updateApplicationDetails(Application_Details appD,
			Application_Details application_Details) {

		// appD.setApplication_doc(application_Details.getApplication_doc());
		appD.setApplication_status(application_Details.getApplication_status());

		return app_DetailsRepository.save(appD);
	}

	// Get an application detail by userId
	public List<Application_Details> findByUserId(String appId) {
		// TODO Auto-generated method stub
		return app_DetailsRepository.findByUserId(appId);
	}

}
