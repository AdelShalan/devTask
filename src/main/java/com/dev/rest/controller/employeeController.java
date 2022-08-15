package com.dev.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.dev.rest.model.Company;
import com.dev.rest.model.Employee;
import com.dev.rest.services.employeeService;

@RestController
public class employeeController {

	private employeeService service;

	@Autowired
	public employeeController(employeeService service) {
		this.service = service;
	}

	public Employee addEmployee(String name, int age, Company company) {
		Employee e = new Employee(company);
		e.setName(name);
		e.setAge(age);
		e.setCompany(company);
		service.addEmployee(e);
		return e;
	}
}