package com.dev.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.dev.rest.services.AddEmployeeService;

@RestController
public class employeeController {

	private AddEmployeeService service;

	@Autowired
	public employeeController(AddEmployeeService service) {
		this.service = service;
	}
}