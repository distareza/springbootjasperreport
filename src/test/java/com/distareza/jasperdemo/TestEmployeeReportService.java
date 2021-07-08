package com.distareza.jasperdemo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEmployeeReportService {

	@Autowired
	private EmployeeReportService report;
	
	@Test
	public void exportPDFReport() throws Exception {
		System.out.println( report.exportReport("pdf") );
		assertTrue(true);
	}
	
	@Test
	public void streamPDFReport() throws Exception {
		System.out.println( report.streamReport("pdf") );
		assertTrue(true);
	}
	
}
