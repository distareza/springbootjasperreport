package com.distareza.jasperdemo.chart;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestRegionChartReportService {

	@Autowired
	private RegionChartReportService chartReport;
	
	@Test
	public void testExportReport() throws Exception {
		chartReport.exportChartReport("pdf");
		chartReport.exportChartReport("html");
		assertTrue(true);
	}
}
