package com.dev.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dev.rest.services.companyService;

@RestController
public class companyController {
	@Autowired
	private companyService service;

	@PostMapping("/api/addCompany")
	public ResponseEntity<String> addCompany(@RequestBody String json) {
		try {
			service.addCompanyJSON(json);
			return ResponseEntity.ok("Company inserted!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Failed to insert company!");
		}
	}

	@GetMapping("/api/getCompany")
	public ResponseEntity<String> getCompany(@RequestParam(name = "companyId", required = true) int companyId) {
		try {
			return ResponseEntity.ok(service.getCompany(companyId).toString());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("NOT FOUND!");
		}
	}
}
