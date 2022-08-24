package com.dev.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.dev.rest.controller.companyController;
import com.dev.rest.model.Company;
import com.dev.rest.model.Employee;
import com.dev.rest.services.CompanyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dev.rest.services.AddEmployeeService;
import com.dev.rest.services.companyService;;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerUnitTest {
	@Autowired
	private AddEmployeeService employeeService;
	@Autowired
	private CompanyServiceImpl addGetCompanyService;
	@Autowired
	private MockMvc mockMvc;

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Before
	public void setUp() throws Exception {
		//this.mockMvc = MockMvcBuilders.standaloneSetup(new companyController()).build();
	}

	@Test
	public void testAddCompany() throws Exception {
		Company c = new Company();
		Employee e = new Employee();

		// create json object of employee and to json array
		e.setId(1);
		e.setAge(25);
		e.setName("Ahmed");
		c.getEmployees().add(e);
		// create another json object of employee and to json array
		e = new Employee();
		e.setId(2);
		e.setAge(38);
		e.setName("Aya");
		c.getEmployees().add(e);

		c.setId(1);
		c.setName("New Company");

		Mockito.when(employeeService.saveEmployeeToDB(e)).thenReturn(true);
		Mockito.when(addGetCompanyService.saveCompanyToDB(c)).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/addCompany").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(c))).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetCompany() throws Exception {
		// create a company
		Company c = new Company();
		c.setId(2);
		c.setName("company x");
		// create first employee and add to company employees
		Employee e = new Employee();
		e.setId(1);
		e.setAge(22);
		e.setName("Jacky");
		c.getEmployees().add(e);
		// create second employee and add to company employees
		e = new Employee();
		e.setId(3);
		e.setAge(36);
		e.setName("Adel");
		c.getEmployees().add(e);
		// mock the DA service
		Mockito.when(addGetCompanyService.getCompanyById(2)).thenReturn(c);
		// mpck a get request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/getCompany").param("companyId", "2"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(
						"Company [id=2, name=company x, employees=[Employee [id=1, name=Jacky, age=22], Employee [id=3, name=Adel, age=36]]]"));

		// fail("Not yet implemented");
	}

}
