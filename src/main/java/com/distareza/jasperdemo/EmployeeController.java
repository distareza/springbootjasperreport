package com.distareza.jasperdemo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private EmployeeReportService report;
	
	@GetMapping("/getEmployee")
	public List<Employee> getEmployee() {	
		return repository.findAll();
	}
	
	@GetMapping("/employee/{id}")
	public Optional<Employee> findEmployee(@PathVariable(value = "id") Long id) {
		return repository.findById(id);
	}
	
	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws Exception {
		return report.exportReport(format);
	}
}
