package com.dev.rest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.dev.rest.controller.companyController;
import com.dev.rest.model.Company;
import com.dev.rest.services.companyService;

@RunWith(SpringRunner.class)
@WebMvcTest(companyController.class)
public class CompanyControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private companyService companyService;

	@Test
	public void validPostRequestTest() throws Exception {
		Company company = new Company();
		JSONObject json = new JSONObject();
		// set company id and name in json object
		json.put("id", 1);
		json.put("name", "New Company");

		// create json object of employee and to json array
		JSONArray employees = new JSONArray();
		JSONObject emp = new JSONObject();
		emp.put("id", 1);
		// emp.put("name", "Ahmed");
		emp.put("age", 25);
		employees.put(emp);
		json.put("employees", employees);

		Mockito.when(companyService.saveCompanyToDB(Mockito.any())).thenReturn(company);

		RequestBuilder request = MockMvcRequestBuilders.post("/api/addCompany").contentType(MediaType.APPLICATION_JSON)
				.content(json.toString());
		mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void validGetRequestTest() throws Exception {
		Company company = new Company();

		Mockito.when(companyService.getCompanyById(Mockito.anyInt())).thenReturn(company);

		RequestBuilder request = MockMvcRequestBuilders.get("/api/getCompany").queryParam("companyId", "1");
		mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
