package com.distareza.jasperdemo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/save/{format}")
	public String generateReport(@PathVariable String format) throws Exception {
		return report.saveReport(format);
	}
	
	@RequestMapping("/export/{format}")
	public ResponseEntity<?> exportDailyOrders(@PathVariable String format) throws Exception {
	    byte[] dailyOrdersBytes = report.streamReport(format);
	    
	    HttpHeaders header = new HttpHeaders();
	    
	    if ("pdf".equalsIgnoreCase(format)) {
		    //header.setContentDispositionFormData("inline", "employee.pdf"); // for download
		    header.add("Content-Disposition", "inline;filename=employee.pdf"); // for opening in browser
		    header.setContentType(MediaType.valueOf("application/pdf"));
	    } else if ("xlsx".equalsIgnoreCase(format)) {
		    header.setContentDispositionFormData("inline", "employee.xlsx");
		    header.setContentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
	    } else {
		    //header.setContentDispositionFormData("inline", "employee.html"); // for download
		    header.add("Content-Disposition", "inline;filename=employee.html");// for opening in browser
		    header.setContentType(MediaType.valueOf("text/html"));
	    }
	    header.setContentLength(dailyOrdersBytes.length);
	    
	    return new ResponseEntity<Object>(dailyOrdersBytes, header, HttpStatus.OK);
	}	
}
