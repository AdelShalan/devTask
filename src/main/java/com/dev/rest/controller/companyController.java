package com.dev.rest.controller;

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

@RestController
public class companyController {
	@Autowired
	private companyService service;

	@Autowired
	public companyController(companyService service) {
		this.service = service;
	}

	@PostMapping("/api/addCompany")
	public ResponseEntity<String> addCompany(@RequestBody String json) {
		Company c = new Company();
		try {
			JSONObject obj = new JSONObject(json);
			c.setName(obj.getString("company name"));
			service.saveEmployees(obj.getJSONArray("employees"), c);
			service.addCompany(c);
			return ResponseEntity.ok("Company inserted!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Failed to insert company!");
		}
	}

	@GetMapping("/api/getCompany")
	public String getCompany(@RequestParam(name = "companyId", required = true) int companyId) {
		try {
			return service.getCompany(companyId).toString();
		} catch (Exception e) {
			return "NOT FOUND!";
		}
	}
}
