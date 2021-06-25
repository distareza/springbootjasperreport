package com.distareza.jasperdemo.chart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RegionChartReportService {

	public class MyCountry {
		
		private String country;
		private Long count;
		
		public MyCountry(String country, Long count) {
			this.country = country;
			this.count = count;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public Long getCount() {
			return count;
		}
		public void setCount(Long count) {
			this.count = count;
		}
		
	}
	
	public String exportChartReport(String format) throws FileNotFoundException, JRException  {
		
		Object[][] arrData = new Object[][] {
			{"France", 77},
			{"Germany", 122},
			{"Brazil", 83},
			{"Belgium", 19},
			{"Switzerland", 18},
			{"Venezuela", 46},
			{"Austria", 40},
			{"Mexico", 28},
			{"USA", 122},
			{"Sweden", 37}			
		};
		
		List<MyCountry> countries = new ArrayList<>();
		for (Object[] data : arrData) {
			countries.add(new MyCountry( data[0].toString(), Long.parseLong(data[1].toString()) ));
		}
		
		// Load the Report Template
		File file = ResourceUtils.getFile("classpath:region_chart.jrxml");		
		JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		// bound data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(countries);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Spring Boot Jasper Report");		
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
		
		if ("pdf".equalsIgnoreCase(format)) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, "/tmp/region_chart.pdf");
		} else {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, "/tmp/region_chart.html");
		}
		
		return "report generated in /tmp folder";
	}
	
}
