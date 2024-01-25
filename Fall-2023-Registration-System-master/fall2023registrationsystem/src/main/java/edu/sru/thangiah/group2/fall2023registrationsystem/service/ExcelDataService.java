package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Activities;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Admin;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Charges;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Location;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Manager;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;

//gets data from the Excel file and converts it to a list for Location

public interface ExcelDataService {
	
	List<Location> getExcelDataAsList();
	int saveExcelData(List<Location> locList);

	List<Activities> getActDataAsList(MultipartFile file);
	int saveActData(List<Activities> actList);

	List<Admin> getAdminDataAsList(MultipartFile file);
	int saveAdminData(List<Admin> adminList);

	List<Charges> getChargeDataAsList(MultipartFile file);
	int saveChargeData(List<Charges> chargeList);

	List<Children> getChildDataAsList(MultipartFile file);
	int saveChildData(List<Children> childList);

	List<Instructor> getInstructDataAsList(MultipartFile file);
	int saveInstructorData(List<Instructor> instructList);

	List<Parent> getParentDataAsList(MultipartFile file);
	int saveParentData(List<Parent> parentList);

	List<Manager> getManagerDataAsList();
	int saveManagerData(List<Manager> saveManagerList);

	Optional<Parent> findById(int parentID);
	void save(Parent parent);

	List<Parent> findAll();
	void delete(int parentID);

}