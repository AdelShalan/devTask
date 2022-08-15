package com.dev.rest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dev.rest.controller.companyController;
import com.dev.rest.model.Company;
import com.dev.rest.model.Employee;
import com.dev.rest.services.companyService;
import com.dev.rest.services.employeeService;

public class companyControllerUnitTest extends RestApplicationTests {
	private employeeService empService;
	private companyService service;

	@Before
	public void setUp() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		companyController controller = new companyController(empService, service);
		
		Company c = new Company();

		Employee e = new Employee(c);
		e.setAge(22);
		e.setName("Jacky");
		c.getEmployees().add(e);

		e = new Employee(c);
		e.setAge(36);
		e.setName("Adel");
		c.getEmployees().add(e);

		c.setId(3);
		c.setName("company x");
	}

	@Test
	public void testAddCompany() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCompany() {
		fail("Not yet implemented");
	}

}
