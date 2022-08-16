package com.dev.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.dev.rest.services.employeeService;

@RestController
public class employeeController {

	private employeeService service;

	@Autowired
	public employeeController(employeeService service) {
		this.service = service;
	}
}