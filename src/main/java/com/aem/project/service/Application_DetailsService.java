package com.aem.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aem.project.entity.Applicant;
import com.aem.project.entity.Application_Details;
import com.aem.project.entity.Job;
import com.aem.project.repository.Application_DetailsRepository;

@Service
public class Application_DetailsService {

	@Autowired
	Application_DetailsRepository app_DetailsRepository;

//	@Autowired
//	Applicant appData;
//
//	@Autowired
//	Job jobData;

	public Application_Details addApplicationDetails(Applicant appData2, Job jobData2, Application_Details appDetails) {
		// TODO Auto-generated method stub

		Application_Details appD = new Application_Details();
		appD.setApplicant(appData2);
		appD.setJob(jobData2);
		appD.setApplication_status(appDetails.getApplication_status());
		appD.setApplication_doc(appDetails.getApplication_doc());

		return app_DetailsRepository.save(appD);
	}

}
