package edu.sru.thangiah.group2.fall2023registrationsystem.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Activities;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Admin;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Charges;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Location;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Manager;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.ExcelDataService;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.UserService;

@Controller
public class UploadController {

	@Autowired
	ExcelDataService excelservice;
	@Autowired
	UserService userService;

	private final String UPLOAD_DIRECTORY = "../fall2023registrationsystem/Program Documents/";

	// display the upload forms for each Excel file
	@GetMapping("/uploadActivity")
	public String uploadActivityForm() {
		return "uploadActivity";
	}

	@GetMapping("/uploadAdmin")
	public String uploadAdminForm() {
		return "uploadAdmin";
	}

	@GetMapping("/uploadManager")
	public String uploadManagerForm() {
		return "uploadManager";
	}

	@GetMapping("/uploadParentChild")
	public String uploadPCForm() {
		return "uploadParentChild";
	}

	@GetMapping("/uploadCharges")
	public String uploadChargesForm() {
		return "uploadCharges";
	}

	@GetMapping("/uploadInstructor")
	public String uploadInstructorForm() {
		return "uploadInstructor";
	}

	// process the uploaded activity Excel file
	@PostMapping("/uploadActivityFile")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException, InvalidFormatException {
	    // check if the uploaded file is empty
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "Please select a file to upload.");
			return "redirect:/uploadActivity";
		}

		try {
	        // extract + save activity data from the uploaded file
			List<Activities> actDataAsList = excelservice.getActDataAsList(file);
			int actRecords = excelservice.saveActData(actDataAsList);

			userService.populateUserRepository();

			// save uploaded file to the specified directory
			Path path = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
			Files.write(path, file.getBytes());

			redirectAttributes.addFlashAttribute("successMessage",
					"File processed successfully. Number of records saved: " + actRecords);
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/uploadActivity";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error processing file: " + e.getMessage());
			return "redirect:/uploadActivity";
		}

		return "redirect:/uploadActivity";
	}

	@PostMapping("/uploadAdminFile")
	public String uploadAdminFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException, InvalidFormatException {
		// check if the uploaded file is empty
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "Please select a file to upload.");
			return "redirect:/uploadAdmin";
		}

		try {
	        // extract + save data from the uploaded file
			List<Admin> adminDataAsList = excelservice.getAdminDataAsList(file);
			int adminRecords = excelservice.saveAdminData(adminDataAsList);

			List<Location> locDataAsList = excelservice.getExcelDataAsList();
			int locRecords = excelservice.saveExcelData(locDataAsList);

			List<Manager> managerDataAsList = excelservice.getManagerDataAsList();
			int managerRecords = excelservice.saveManagerData(managerDataAsList);

			// save uploaded file to the specified directory
			Path path = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
			Files.write(path, file.getBytes());

			userService.populateUserRepository();
			int totalRecords = locRecords + adminRecords + managerRecords;

			redirectAttributes.addFlashAttribute("successMessage",
					"File processed successfully. Number of records saved: " + totalRecords);
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/uploadAdmin";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error processing file: " + e.getMessage());
			return "redirect:/uploadAdmin";
		}

		return "redirect:/uploadAdmin";
	}

	@PostMapping("/uploadChargesFile")
	public String uploadChargesFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException, InvalidFormatException {
		// check if the uploaded file is empty
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "Please select a file to upload.");
			return "redirect:/uploadCharges";
		}

		try {
			// extract + save data from the uploaded file
			List<Charges> chargeDataAsList = excelservice.getChargeDataAsList(file);
			int chargeRecords = excelservice.saveChargeData(chargeDataAsList);

			userService.populateUserRepository();

			// save uploaded file to the specified directory
			Path path = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
			Files.write(path, file.getBytes());

			redirectAttributes.addFlashAttribute("successMessage",
					"File processed successfully. Number of records saved: " + chargeRecords);

		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/uploadCharges";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error processing file: " + e.getMessage());
			return "redirect:/uploadCharges";
		}

		return "redirect:/uploadCharges";
	}

	@PostMapping("/uploadInstructorFile")
	public String uploadInstructorFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException, InvalidFormatException {
		// check if the uploaded file is empty
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "Please select a file to upload.");
			return "redirect:/uploadInstructor";
		}

		try {
			// extract + save data from the uploaded file
			List<Instructor> instructDataAsList = excelservice.getInstructDataAsList(file);
			int instructRecords = excelservice.saveInstructorData(instructDataAsList);

			userService.populateUserRepository();

			// save uploaded file to the specified directory
			Path path = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
			Files.write(path, file.getBytes());

			redirectAttributes.addFlashAttribute("successMessage",
					"File processed successfully. Number of records saved: " + instructRecords);

		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/uploadInstructor";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error processing file: " + e.getMessage());
			return "redirect:/uploadInstructor";
		}

		return "redirect:/uploadInstructor";
	}

	@PostMapping("/uploadParentChildFile")
	public String uploadParentChildFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException, InvalidFormatException {
		// check if the uploaded file is empty
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "Please select a file to upload.");
			return "redirect:/uploadParentChildFile";
		}

		try {
			// extract + save data from the uploaded file
			List<Children> childDataAsList = excelservice.getChildDataAsList(file);
			int childRecords = excelservice.saveChildData(childDataAsList);
			
			List<Parent> parentDataAsList = excelservice.getParentDataAsList(file);
			int parentRecords = excelservice.saveParentData(parentDataAsList);
			
			int totalRecords = childRecords + parentRecords;

			userService.populateUserRepository();

			// save uploaded file to the specified directory
			Path path = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
			Files.write(path, file.getBytes());

			redirectAttributes.addFlashAttribute("successMessage",
					"File processed successfully. Number of records saved: " + totalRecords);

		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/uploadParentChild";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error processing file: " + e.getMessage());
			return "redirect:/uploadParentChild";
		}

		return "redirect:/uploadParentChild";
	}
}