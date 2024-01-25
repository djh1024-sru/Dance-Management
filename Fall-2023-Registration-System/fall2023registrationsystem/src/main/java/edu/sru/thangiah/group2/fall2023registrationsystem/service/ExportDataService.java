package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Activities;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Admin;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Charges;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Location;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Manager;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ActivitiesRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.AdminRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ChargesRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ChildRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.InstructorRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.LocationRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ManagerRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;

@Service
public class ExportDataService {

	@Autowired
	LocationRepository locRepo;
	@Autowired
	ActivitiesRepository actRepo;
	@Autowired
	AdminRepository adminRepo;
	@Autowired
	ChargesRepository chargeRepo;
	@Autowired
	ChildRepository childRepo;
	@Autowired
	InstructorRepository instructRepo;
	@Autowired
	ManagerRepository managerRepo;
	@Autowired
	ParentRepository parentRepo;

	public void exportAMLDataToExcel() throws Exception {
		Workbook admin_manager_location_workbook = new XSSFWorkbook();
		Sheet adminSheet = admin_manager_location_workbook.createSheet("admin");

		List<Admin> adminList = adminRepo.findAll();

		// write header
		Row adminHeaderRow = adminSheet.createRow(0);
		Field[] adminFields = Admin.class.getDeclaredFields();
		for (int i = 0; i < adminFields.length; i++) {
			Cell adminCell = adminHeaderRow.createCell(i);
			adminCell.setCellValue(adminFields[i].getName());
		}

		// write data
		for (int i = 0; i < adminList.size(); i++) {
			Admin adminEntity = adminList.get(i);
			Row adminRow = adminSheet.createRow(i + 1);
			for (int j = 0; j < adminFields.length; j++) {
				adminFields[j].setAccessible(true);
				Object value = adminFields[j].get(adminEntity);
				Cell cell = adminRow.createCell(j);
				if (value != null) {
					cell.setCellValue(value.toString());
				}
			}
		}

		Sheet managerSheet = admin_manager_location_workbook.createSheet("manager");

		List<Manager> managerList = managerRepo.findAll();

		// write header
		Row managerHeaderRow = managerSheet.createRow(0);
		Field[] managerFields = Manager.class.getDeclaredFields();
		for (int i = 0; i < managerFields.length; i++) {
			Cell managerCell = managerHeaderRow.createCell(i);
			managerCell.setCellValue(managerFields[i].getName());
		}

		// write data
		for (int i = 0; i < managerList.size(); i++) {
			Manager managerEntity = managerList.get(i);
			Row managerRow = managerSheet.createRow(i + 1);
			for (int j = 0; j < managerFields.length; j++) {
				managerFields[j].setAccessible(true);
				Object value = managerFields[j].get(managerEntity);
				Cell cell = managerRow.createCell(j);
				if (value != null) {
					cell.setCellValue(value.toString());
				}
			}
		}

		Sheet locationSheet = admin_manager_location_workbook.createSheet("location");

		List<Location> dataList = locRepo.findAll();

		// write header
		Row headerRow = locationSheet.createRow(0);
		Field[] fields = Location.class.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(fields[i].getName());
		}

		// write data
		for (int i = 0; i < dataList.size(); i++) {
			Location entity = dataList.get(i);
			Row row = locationSheet.createRow(i + 1);

			for (int j = 0; j < fields.length; j++) {
				fields[j].setAccessible(true);
				Object value = fields[j].get(entity);
				Cell cell = row.createCell(j);
				if (value != null) {
					cell.setCellValue(value.toString());
				}
			}
		}

		// write the output to a file
		try (FileOutputStream fileOut = new FileOutputStream(
				"../fall2023registrationsystem/Program Documents/Export Files/exportAdminManagerLocation.xlsx")) {
			admin_manager_location_workbook.write(fileOut);
			admin_manager_location_workbook.close();
		}
	}

	public void exportInstructorDataToExcel() throws Exception {
		Workbook instructorWorkbook = new XSSFWorkbook();
		Sheet instructorSheet = instructorWorkbook.createSheet("instructors");

		List<Instructor> instructorList = instructRepo.findAll();

		// write header
		Row instructorHeaderRow = instructorSheet.createRow(0);
		Field[] instructorFields = Instructor.class.getDeclaredFields();
		for (int i = 0; i < instructorFields.length; i++) {
			Cell instructorCell = instructorHeaderRow.createCell(i);
			instructorCell.setCellValue(instructorFields[i].getName());
		}

		// write data
		for (int i = 0; i < instructorList.size(); i++) {
			Instructor instructorEntity = instructorList.get(i);
			Row instructorRow = instructorSheet.createRow(i + 1);
			for (int j = 0; j < instructorFields.length; j++) {
				instructorFields[j].setAccessible(true);
				Object value = instructorFields[j].get(instructorEntity);
				Cell cell = instructorRow.createCell(j);
				if (value != null) {
					cell.setCellValue(value.toString());
				}
			}
		}

		// write the output to a file
		try (FileOutputStream fileOut = new FileOutputStream(
				"../fall2023registrationsystem/Program Documents/Export Files/exportInstructor.xlsx")) {
			instructorWorkbook.write(fileOut);
			instructorWorkbook.close();
		}
	}

	public void exportActivityDataToExcel() throws Exception {
		Workbook activityWorkbook = new XSSFWorkbook();
		Sheet activitySheet = activityWorkbook.createSheet("activity");

		List<Activities> activityList = actRepo.findAll();

		// write header
		Row activityHeaderRow = activitySheet.createRow(0);
		Field[] activityFields = Activities.class.getDeclaredFields();
		for (int i = 0; i < activityFields.length; i++) {
			Cell activityCell = activityHeaderRow.createCell(i);
			activityCell.setCellValue(activityFields[i].getName());
		}

		// write data
		for (int i = 0; i < activityList.size(); i++) {
			Activities activityEntity = activityList.get(i);
			Row activityRow = activitySheet.createRow(i + 1);
			for (int j = 0; j < activityFields.length; j++) {
				activityFields[j].setAccessible(true);
				Object value = activityFields[j].get(activityEntity);
				Cell cell = activityRow.createCell(j);
				if (value != null) {
					cell.setCellValue(value.toString());
				}
			}
		}

		// write the output to a file
		try (FileOutputStream fileOut = new FileOutputStream(
				"../fall2023registrationsystem/Program Documents/Export Files/exportActivity.xlsx")) {
			activityWorkbook.write(fileOut);
			activityWorkbook.close();
		}
	}

	public void exportChargesDataToExcel() throws Exception {
		Workbook chargesWorkbook = new XSSFWorkbook();
		Sheet chargesSheet = chargesWorkbook.createSheet("charges");

		List<Charges> chargesList = chargeRepo.findAll();

		// write header
		Row chargesHeaderRow = chargesSheet.createRow(0);
		Field[] chargesFields = Charges.class.getDeclaredFields();
		for (int i = 0; i < chargesFields.length; i++) {
			Cell chargesCell = chargesHeaderRow.createCell(i);
			chargesCell.setCellValue(chargesFields[i].getName());
		}

		// write data
		for (int i = 0; i < chargesList.size(); i++) {
			Charges chargesEntity = chargesList.get(i);
			Row chargesRow = chargesSheet.createRow(i + 1);
			for (int j = 0; j < chargesFields.length; j++) {
				chargesFields[j].setAccessible(true);
				Object value = chargesFields[j].get(chargesEntity);
				Cell cell = chargesRow.createCell(j);
				if (value != null) {
					cell.setCellValue(value.toString());
				}
			}
		}

		// write the output to a file
		try (FileOutputStream fileOut = new FileOutputStream(
				"../fall2023registrationsystem/Program Documents/Export Files/exportCharges.xlsx")) {
			chargesWorkbook.write(fileOut);
			chargesWorkbook.close();
		}
	}

	public void exportParentChildDataToExcel() throws Exception {
		Workbook parentChildWorkbook = new XSSFWorkbook();
		Sheet parentSheet = parentChildWorkbook.createSheet("parent");

		List<Parent> parentList = parentRepo.findAll();

		// write header
		Row parentHeaderRow = parentSheet.createRow(0);
		Field[] parentFields = Parent.class.getDeclaredFields();
		for (int i = 0; i < parentFields.length; i++) {
			Cell parentCell = parentHeaderRow.createCell(i);
			parentCell.setCellValue(parentFields[i].getName());
		}

		// write data
		for (int i = 0; i < parentList.size(); i++) {
			Parent parentEntity = parentList.get(i);
			Row parentRow = parentSheet.createRow(i + 1);
			for (int j = 0; j < parentFields.length; j++) {
				parentFields[j].setAccessible(true);
				Object value = parentFields[j].get(parentEntity);
				Cell cell = parentRow.createCell(j);
				if (value != null) {
					cell.setCellValue(value.toString());
				}
			}
		}

		Sheet childSheet = parentChildWorkbook.createSheet("child registration");

		List<Children> childList = childRepo.findAll();

		// write header
		Row childHeaderRow = childSheet.createRow(0);
		Field[] childFields = Children.class.getDeclaredFields();
		for (int i = 0; i < childFields.length; i++) {
			Cell childCell = childHeaderRow.createCell(i);
			childCell.setCellValue(childFields[i].getName());
		}

		// write data
		for (int i = 0; i < childList.size(); i++) {
			Children childEntity = childList.get(i);
			Row childRow = childSheet.createRow(i + 1);
			for (int j = 0; j < childFields.length; j++) {
				childFields[j].setAccessible(true);
				Object value = childFields[j].get(childEntity);
				Cell cell = childRow.createCell(j);
				if (value != null) {
					cell.setCellValue(value.toString());
				}
			}
		}

		// write the output to a file
		try (FileOutputStream fileOut = new FileOutputStream(
				"../fall2023registrationsystem/Program Documents/Export Files/exportParentChild.xlsx")) {
			parentChildWorkbook.write(fileOut);
			parentChildWorkbook.close();
		}
	}
}
