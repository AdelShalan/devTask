package com.dev.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.dev.rest.controller.companyController;
import com.dev.rest.model.Company;
import com.dev.rest.model.Employee;
import com.dev.rest.services.AddGetCompanyService;
import com.dev.rest.services.AddEmployeeService;

public class CompanyControllerUnitTest {
	private AddEmployeeService empService = Mockito.mock(AddEmployeeService.class, Mockito.RETURNS_DEEP_STUBS);
	private AddGetCompanyService service = Mockito.mock(AddGetCompanyService.class, Mockito.RETURNS_DEEP_STUBS);
	private String requestString;
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new companyController(service)).build();
	}

	@Test
	public void testAddCompany() throws Exception {
		JSONArray empJson = new JSONArray();
		JSONObject o = new JSONObject();
		Company c = new Company();
		Employee e = new Employee();

		// create json object of employee and to json array
		o.put("id", 1);
		o.put("name", "Jacky");
		o.put("age", 22);
		empJson.put(o);
		// create another json object of employee and to json array
		o = new JSONObject();
		o.put("id", 3);
		o.put("name", "John");
		o.put("age", 33);
		empJson.put(o);

		requestString = new JSONObject().put("company name", "").put("employees", empJson).toString();

		Mockito.when(empService.saveEmployeeToDB(e)).thenReturn(true);
		Mockito.when(service.saveCompanyToDB(c)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/addCompany").contentType(MediaType.APPLICATION_JSON)
				.content(requestString)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetCompany() throws Exception {
		// create a company
		Company c = new Company();
		c.setId(2);
		c.setName("company x");
		// create first employee and add to company employees
		Employee e = new Employee(c);
		e.setId(1);
		e.setAge(22);
		e.setName("Jacky");
		c.getEmployees().add(e);
		// create second employee and add to company employees
		e = new Employee(c);
		e.setId(3);
		e.setAge(36);
		e.setName("Adel");
		c.getEmployees().add(e);
		// mock the DA service
		Mockito.when(service.getCompanyById(2)).thenReturn(c);
		// mpck a get request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/getCompany").param("companyId", "2"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(
						"Company [id=2, name=company x, employees=[Employee [id=1, name=Jacky, age=22], Employee [id=3, name=Adel, age=36]]]"));

		// fail("Not yet implemented");
	}

}
