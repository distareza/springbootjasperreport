package com.distareza.jasperdemo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEmployeeRepository {

	@Autowired
	private EmployeeRepository repository;
	
	@Test
	public void initEmployeeData() throws Exception {
		
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Object[][] arrData = new Object[][] {
			{ 1, "Astrid", 	"Tester", 			"1967-09-17 08:09:14",	5000},
			{ 2, "Brad", 	"Java Developer", 	"1936-08-09 10:25:50",	8000},
			{ 3, "Choe", 	"System Analyst", 	"1905-04-10 12:48:38",	9000},
			{ 4, "Daniel", 	"Project Manager", 	"2098-09-26 20:38:57",	12000},
			{ 5, "Eugine", 	"UI Developer", 	"1950-05-23 01:56:14",	7000},
			{ 6, "Fiona", 	"Technical Lead", 	"2073-11-24 19:55:34",	10000},
			{ 7, "Gina", 	"Infrastructure", 	"2087-03-21 01:03:22",	80000},
			{ 8, "Horst", 	"Database Engineer", 	"1967-09-17 00:13:28",	75000},
			{ 9, "Irene", 	"Graphic Designer", 	"2053-11-19 06:44:49",	65000},
			{10, "Jeremy", 	"Java Developer", 	"2067-02-22 19:43:39",	85000}
		};

		for (Object[] data : arrData) {
			Employee employee = new Employee();
			employee.setId(Long.parseLong(data[0].toString()));
			employee.setName(data[1].toString());
			employee.setJob(data[2].toString());
			employee.setDateOfJoin( sdf.parse( data[3].toString() ) );
			employee.setSalary(Double.parseDouble( data[4].toString() ));
			
			repository.save(employee);
		}
		
	}
	
	
	@Test
	public void testGetEmployee() {
		System.out.println( repository.findAll() );
		assertTrue(true);
	}
	
}
