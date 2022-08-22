package com.dev.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.rest.exception.ApiRequestException;
import com.dev.rest.model.Company;
import com.dev.rest.services.companyService;

@RestController
public class companyController {
	@Autowired
	private companyService service;

	@PostMapping(value = "/api/addCompany", consumes = { "application/json" })
	public ResponseEntity<String> addCompany(@RequestBody(required = true) Company companyJson) throws Exception {
		try {
			service.addCompanyJSON(companyJson);
			return ResponseEntity.ok("Company inserted!");
		} catch (ApiRequestException e) {
			throw e;
		}
	}

	@GetMapping("/api/getCompany")
	public ResponseEntity<String> getCompany(@RequestParam(name = "companyId", required = true) int companyId) {
		try {
			return ResponseEntity.ok(service.getCompanyById(companyId).toString());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Company NOT found!");
		}
	}
}
