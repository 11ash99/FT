package com.ft.api;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class ServerInitializer implements ApplicationRunner {
	
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		
		System.out.println("Server started");
		
    }

	

}
