package com.ft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


import com.ft.api.service.FTService;

@Component
public class ServerInitializer implements ApplicationRunner {
	
	@Autowired
    FTService serviceImpl;
	
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		
		serviceImpl.publishData();
		
    }

	

}
