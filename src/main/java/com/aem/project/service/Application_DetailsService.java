package com.aem.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aem.project.entity.Application_Details;
import com.aem.project.repository.Application_DetailsRepository;

@Service
public class Application_DetailsService {

	@Autowired
	Application_DetailsRepository app_DetailsRepository;

	public Application_Details addApplicationDetails(Application_Details appDetails) {
		// TODO Auto-generated method stub
		return app_DetailsRepository.save(appDetails);
	}

}
