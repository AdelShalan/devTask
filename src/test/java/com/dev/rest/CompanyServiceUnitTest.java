package com.dev.rest;

import static org.junit.Assert.*;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.dev.rest.dao.companyDao;
import com.dev.rest.exception.ApiRequestException;
import com.dev.rest.model.Company;
import com.dev.rest.model.Employee;
import com.dev.rest.services.companyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyServiceUnitTest {
	@MockBean
	private companyDao companyDao;
	@Autowired
	private companyService companyService;

	@Test
	public void testAddCompany() {
		Company company = new Company();
		Employee employee = new Employee();

		employee.setId(1);
		employee.setAge(25);
		employee.setName("Ahmed");
		
		company.getEmployees().add(employee);
		
		employee = new Employee();
		employee.setId(2);
		employee.setAge(38);
		employee.setName("Aya");
		
		company.getEmployees().add(employee);
		company.setId(1);
		company.setName("New Company");
		
		Mockito.when(companyDao.existsById(Mockito.anyInt())).thenReturn(false);
		Mockito.when(companyDao.save(company)).thenReturn(company);

		try {
			assertEquals(company, companyService.saveCompanyToDB(company));
		} catch (Exception e) {
			fail("Company not saved when it should have been!");
			e.printStackTrace();
		}
	}

	@Test(expected = ApiRequestException.class)
	public void testAddCompanyExpectedError() throws ApiRequestException, Exception {
		Company company = new Company();

		Mockito.when(companyDao.existsById(Mockito.anyInt())).thenReturn(true);
		Mockito.when(companyDao.save(company)).thenReturn(company);

		companyService.saveCompanyToDB(company);
	}
	
	@Test
	public void testGetCompany() {
		Company company = new Company();
		Employee employee = new Employee();

		employee.setId(1);
		employee.setAge(25);
		employee.setName("Ahmed");
		
		company.getEmployees().add(employee);
		
		employee = new Employee();
		employee.setId(2);
		employee.setAge(38);
		employee.setName("Aya");
		
		company.getEmployees().add(employee);
		company.setId(1);
		company.setName("New Company");

		Mockito.when(companyDao.findById(1)).thenReturn(Optional.of(company));
		assertEquals(companyService.getCompanyById(company.getId()), company);
	}

}
