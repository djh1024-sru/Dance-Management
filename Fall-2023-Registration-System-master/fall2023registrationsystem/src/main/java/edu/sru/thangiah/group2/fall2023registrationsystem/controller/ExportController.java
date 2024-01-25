package edu.sru.thangiah.group2.fall2023registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.sru.thangiah.group2.fall2023registrationsystem.service.ExportDataService;

// handling data export requests
@Controller
@RequestMapping("/export")
public class ExportController {

	@Autowired
	private ExportDataService exportService;

	// exporting admin data to Excel
	@PostMapping("/adminData")
	public String exportData() {
		try {
			// invoke service method to export admin data to Excel
			exportService.exportAMLDataToExcel();
			return "exportSuccessful";
		} catch (Exception e) {
			e.printStackTrace();
			return "Export failed: " + e.getMessage();
		}
	}

	// exporting instructor data to Excel
	@PostMapping("/instructorData")
	public String exportInstructorData() {
		try {
			// invoke service method to export instructor data to Excel
			exportService.exportInstructorDataToExcel();
			return "exportSuccessful";
		} catch (Exception e) {
			e.printStackTrace();
			return "Export failed: " + e.getMessage();
		}
	}

	// exporting charges data to Excel
	@PostMapping("/chargesData")
	public String exportChargesData() {
		try {
			// invoke service method to export charges data to Excel
			exportService.exportChargesDataToExcel();
			return "exportSuccessful";
		} catch (Exception e) {
			e.printStackTrace();
			return "Export failed: " + e.getMessage();
		}
	}

	// exporting parent-child data to Excel
	@PostMapping("/parentChildData")
	public String exportParentChildData() {
		try {
			// invoke service method to export parent-child data to Excel
			exportService.exportParentChildDataToExcel();
			return "exportSuccessful";
		} catch (Exception e) {
			e.printStackTrace();
			return "Export failed: " + e.getMessage();
		}
	}

	// exporting activity data to Excel
	@PostMapping("/activityData")
	public String exportActivityData() {
		try {
			// invoke service method to export activity data to Excel
			exportService.exportActivityDataToExcel();
			return "exportSuccessful";
		} catch (Exception e) {
			e.printStackTrace();
			return "Export failed: " + e.getMessage();
		}
	}
}
