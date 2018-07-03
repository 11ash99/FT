package com.ft.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ft.api.service.FTService;

@ControllerAdvice
@RestController
@RequestMapping(value = "/api", headers = "Accept=application/json")
@Component
public class FTController implements FTControllerInterface {
	
	@Autowired
    FTService serviceImpl;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/publishing", method = RequestMethod.GET, produces = "application/json")
	public String publishData(){
		return serviceImpl.publishData();
	}

}
