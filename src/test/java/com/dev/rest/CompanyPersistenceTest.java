package com.dev.rest;

import static org.junit.Assert.*;
import javax.validation.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.dev.rest.exception.ApiRequestException;
import com.dev.rest.model.Company;
import com.dev.rest.model.Employee;
import com.dev.rest.services.companyService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CompanyPersistenceTest {
	@Autowired
	private companyService companyService;

	@Test
	public void saveCompany() throws ApiRequestException, Exception {
		Company company = new Company();
		Employee employee = new Employee();

		employee.setAge(25);
		employee.setName("Ahmed");

		company.getEmployees().add(employee);

		employee = new Employee();
		employee.setAge(38);
		employee.setName("Aya");

		company.getEmployees().add(employee);
		company.setId(1);
		company.setName("New Company");

		assertNotNull(companyService.saveCompanyToDB(company));
	}

	@Test(expected = ConstraintViolationException.class)
	public void saveCompayWithMissingData() throws ApiRequestException, Exception {
		Company company = new Company();
		Employee employee = new Employee();

		employee.setId(3);
		employee.setAge(25);
		employee.setName(""); // Empty Name

		company.getEmployees().add(employee);

		employee = new Employee();
		employee.setAge(38);
		employee.setName("Aya");

		company.getEmployees().add(employee);
		company.setId(2);
		company.setName("New Company 2");

		companyService.saveCompanyToDB(company);
	}

	@Test(expected = ApiRequestException.class)
	public void saveExistingCompany() throws ApiRequestException, Exception {
		Company company = new Company();
		Employee employee = new Employee();

		employee.setAge(25);
		employee.setName("Ahmed");

		company.getEmployees().add(employee);

		employee = new Employee();
		employee.setAge(38);
		employee.setName("Aya");

		company.getEmployees().add(employee);
		company.setId(3);
		company.setName("New Company 3");

		companyService.saveCompanyToDB(company);
		companyService.saveCompanyToDB(company);

	}

}
