package com.ft.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ft.api.service.FTService;
import com.ft.api.service.FTServiceImpl;

@Configuration
public class FTConfig {
	
	@Bean
	public FTService serviceImpl() {
		return new FTServiceImpl();
	}

}
