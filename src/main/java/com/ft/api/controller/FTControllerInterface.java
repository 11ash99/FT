package com.ft.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface FTControllerInterface {
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/publishing", method = RequestMethod.GET, produces = "application/json")
	public String publishData();

}
