package com.distareza.jasperdemo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class EmployeeReportService {

	@Autowired
	private EmployeeRepository repository;

	public String saveReport(String format) throws FileNotFoundException, JRException {
		List<Employee> employees = repository.findAll();
		
		// Load the Report Template
		File file = ResourceUtils.getFile("classpath:employee.jrxml");		
		JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		// bound data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", String.format("Created By : %s Spring Boot Jasper Report", new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss").format(new Date())));		
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
		
		if ("pdf".equalsIgnoreCase(format)) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, "/tmp/employee.pdf");
		} else {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, "/tmp/employee.html");
		}
		
		return "report generated in /tmp folder";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] streamReport(String format) throws Exception {
		List<Employee> employees = repository.findAll();
		
		// Load the Report Template
		File file = ResourceUtils.getFile("classpath:employee.jrxml");		
		JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		// bound data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", String.format("Created By : %s Spring Boot Jasper Report", new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss").format(new Date())));		
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		Exporter exporter;
		if ("pdf".equalsIgnoreCase(format)) {
			exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		} else if ("xlsx".equalsIgnoreCase(format)) {
			exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
		} else {
			exporter = new HtmlExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleHtmlExporterOutput(byteArrayOutputStream));
		}
		exporter.exportReport();
		
		return byteArrayOutputStream.toByteArray();
	}
	
}
