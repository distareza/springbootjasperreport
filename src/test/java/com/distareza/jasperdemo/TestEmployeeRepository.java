package com.distareza.jasperdemo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEmployeeRepository {

	@Autowired
	private EmployeeRepository repository;
	
	@Test
	public void testGetEmployee() {
		System.out.println( repository.findAll() );
		assertTrue(true);
	}
	
}
