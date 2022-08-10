package com.aem.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aem.project.entity.User;
import com.aem.project.entity.Application_Details;
import com.aem.project.entity.Job;
import com.aem.project.repository.Application_DetailsRepository;

@Service
public class Application_DetailsService {

	@Autowired
	Application_DetailsRepository app_DetailsRepository;

//	@Autowired
//	User appData;
//
//	@Autowired
//	Job jobData;

	public Application_Details addApplicationDetails(User appData2, Job jobData2, Application_Details appDetails) {
		// TODO Auto-generated method stub

		Application_Details appD = new Application_Details();
		appD.setUser(appData2);
		appD.setJob(jobData2);
		appD.setApplication_status(appDetails.getApplication_status());
		appD.setApplication_doc(appDetails.getApplication_doc());

		return app_DetailsRepository.save(appD);
	}

	public Application_Details getApplicationDetailById(String appDetailId) {
		// TODO Auto-generated method stub
		return app_DetailsRepository.findById(appDetailId).get();
	}

	public List<Application_Details> getApplicationDetails() {
		// TODO Auto-generated method stub
		return app_DetailsRepository.findAll();
	}

	public List<Application_Details> getAllApplications(String applicantId) {
		// TODO Auto-generated method stub
		return app_DetailsRepository.findByUserId(applicantId);
	}

	public Application_Details getApplicationDetailsById(String appDetail_Id) {
		// TODO Auto-generated method stub
		return app_DetailsRepository.findById(appDetail_Id).get();
	}

	public Application_Details updateApplicationDetails(Application_Details appD,
			Application_Details application_Details) {

		appD.setApplication_doc(application_Details.getApplication_doc());
		appD.setApplication_status(application_Details.getApplication_status());

		return app_DetailsRepository.save(appD);
	}

}
