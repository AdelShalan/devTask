package com.dev.rest.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dev.rest.model.Company;
import com.dev.rest.services.companyService;
import com.dev.rest.services.employeeService;

@RestController
public class companyController {
	private employeeService empService;
	private companyService service;

	@Autowired
	public companyController(employeeService empService, companyService service) {
		this.empService = empService;
		this.service = service;
	}

	@PostMapping("/api/addCompany")
	public Company addCompany(@RequestBody String json) throws JSONException {
		Company c = new Company();
		JSONObject obj = new JSONObject(json);
		c.setName(obj.getString("company name"));
		empService.addEmployees(obj.getJSONArray("employees"), c);
		service.addCompany(c);
		return c;
	}

	@GetMapping("/api/getCompany")
	public ResponseEntity<Company> getCompany(@RequestParam(name = "companyId", required = true) int companyId) {
		Company c = service.getCompany(companyId);
		if (c == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(c);
		}
	}
}
