
package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class ExcelDataServiceImpl implements ExcelDataService {

	// stores path to where the uploaded excel files are stored
	@Value("${app.upload.adminPath:${user.home}}")
	public String ADMIN_FILE_PATH;

	@Value("${app.upload.parentChildPath:${user.home}}")
	public String PARENTCHILD_FILE_PATH;

	@Value("${app.upload.activitiesPath:${user.home}}")
	public String ACTIVITIES_FILE_PATH;

	@Value("${app.upload.instructorPath:${user.home}}")
	public String INSTRUCTOR_FILE_PATH;

	@Value("${app.upload.chargesPath:${user.home}}")
	public String CHARGES_FILE_PATH;

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

	// workbook object that holds the uploaded excel content
	Workbook Activities;
	Workbook Admin;
	Workbook Instructor;
	Workbook ParentChild;
	Workbook Charges;

	@Override
	public List<Location> getExcelDataAsList() {

		List<String> list = new ArrayList<>();

		// format each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// create an work though Workbook
		try {
			Admin = WorkbookFactory.create(new File(ADMIN_FILE_PATH));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		Sheet sheet = Admin.getSheetAt(2);
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		for (Row row : sheet) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				list.add(cellValue);
			}
		}
		System.out.println("data:: " + list);

		// filling excel data - creating list as List<Location>
		List<Location> locList = createList(list, noOfColumns);

		// closing the workbook
		try {
			Admin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return locList;
	}

	private List<Location> createList(List<String> excelData, int noOfColumns) {

		ArrayList<Location> locList = new ArrayList<>();

		int i = noOfColumns;

		do {
			Location loc = new Location();

			loc.setStudioID(Integer.valueOf(excelData.get(i)));
			loc.setLocationName(excelData.get(i + 1));
			loc.setAddress(excelData.get(i + 2));
			loc.setAddress2(excelData.get(i + 3));
			loc.setCity(excelData.get(i + 4));
			loc.setState(excelData.get(i + 5));
			loc.setZip(excelData.get(i + 6));
			loc.setPhoneNum(String.valueOf(excelData.get(i + 7)));

			locList.add(loc);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		return locList;
	}

	@Override
	public int saveExcelData(List<Location> saveLocList) {
		saveLocList = locRepo.saveAll(saveLocList);
		return saveLocList.size();
	}

	@Override
	public List<Activities> getActDataAsList(MultipartFile file) {

		List<String> activityList = new ArrayList<>();
		List<String> masterActivityList = new ArrayList<>();

		DataFormatter dataFormatter = new DataFormatter();

		int noOfColumns = 0;

		// create and work though Workbook
		try (Workbook Activities = WorkbookFactory.create(file.getInputStream())) {
			for (int sheetIndex = 0; sheetIndex < Activities.getNumberOfSheets(); sheetIndex++) {
				Sheet sheet = Activities.getSheetAt(sheetIndex);

				if (sheetIndex == 0) {
					String firstCellValue = dataFormatter.formatCellValue(sheet.getRow(0).getCell(0));
					if (!firstCellValue.equals("ACTIVITIES")) {
						throw new RuntimeException("The file selected is not the correct ACTIVITIES file. Please select the correct ACTIVITIES file.");
					}
				}

				noOfColumns = sheet.getRow(1).getLastCellNum(); 
				System.out.println("Reading sheet " + sheetIndex + " with " + noOfColumns + " columns.");

				for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					List<String> rowData = new ArrayList<>();
					for (Cell cell : row) {
						String cellValue = dataFormatter.formatCellValue(cell);
						rowData.add(cellValue);
					}
					masterActivityList.addAll(rowData);
				}		
				System.out.println("Finished reading sheet " + sheetIndex + ". Data size: " + activityList.size());
				masterActivityList.addAll(activityList);
				
				activityList.clear();
			}
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		// filling excel data - creating list as List<Activities>
		List<Activities> actList = createactList(masterActivityList, noOfColumns);
		return actList;
	}

	private List<Activities> createactList(List<String> excelData, int noOfColumns) {

		ArrayList<Activities> actList = new ArrayList<>();

		int uniqueSuffix = 0;
		int i = noOfColumns;

		do  {
			Activities act = new Activities();

			String originalID = excelData.get(i + 6);
			String uniqueID = originalID + "-" + uniqueSuffix++;
			act.setActivityID(uniqueID);

			act.setLocationName(excelData.get(i + 1));
			act.setWeekDay(excelData.get(i + 2));
			
			String startTimeString = excelData.get(i + 3);
			String endTimeString = excelData.get(i + 4);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime startTime = LocalTime.parse(startTimeString, formatter);
			LocalTime endTime = LocalTime.parse(endTimeString, formatter);
			
			act.setStartTime(startTime);
			act.setEndTime(endTime);
			
			act.setActivityLevel(excelData.get(i + 5));
			act.setInstructorID(Integer.valueOf(excelData.get(i + 7)));
			actList.add(act);
			i = i + (noOfColumns);
		}
		while (i < excelData.size());

		return actList;
	}

	@Override
	public int saveActData(List<Activities> saveActList) {
		saveActList = actRepo.saveAll(saveActList);
		return saveActList.size();
	}

	@Override
	public List<Admin> getAdminDataAsList(MultipartFile file) {

		List<String> adList = new ArrayList<>();

		// format each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// create an work though Workbook
		try {
			Admin = WorkbookFactory.create(file.getInputStream());
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		Sheet sheet = Admin.getSheetAt(0);

		String firstCellValue = dataFormatter.formatCellValue(sheet.getRow(0).getCell(0));
		if (!firstCellValue.equals("ADMIN")) {
			throw new RuntimeException("The file selected is not the correct ADMIN file. Please select the correct ADMIN file.");
		}

		int noOfColumns = sheet.getRow(1).getLastCellNum();

		for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				adList.add(cellValue);
			}
		}

		// filling excel data - creating list as List<Admin>
		List<Admin> adminList = createadminList(adList, noOfColumns);

		return adminList;
	}

	private List<Admin> createadminList(List<String> excelData, int noOfColumns) {

		ArrayList<Admin> adminList = new ArrayList<>();

		int i = 0;
		do {
			Admin admin = new Admin();

			if (i + 4 < excelData.size()) {
				admin.setAdminID(excelData.get(i + 4));
				admin.setLastName(excelData.get(i));
				admin.setFirstName(excelData.get(i + 1));
				admin.setEmail(excelData.get(i + 2));
				admin.setAdminPhoneNum(excelData.get(i + 3));

				adminList.add(admin);
			}
			i = i + (noOfColumns);

		} while (i <= excelData.size());
		return adminList;
	}

	@Override
	public int saveAdminData(List<Admin> saveAdminData) {
		saveAdminData = adminRepo.saveAll(saveAdminData);
		return saveAdminData.size();
	}

	@Override
	public List<Charges> getChargeDataAsList(MultipartFile file) {

		List<String> chList = new ArrayList<>();

		// format each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		int noOfColumns = 0;

		// create an work though Workbook
		try (Workbook Charges = WorkbookFactory.create(file.getInputStream())) {
			int sheetIndex = 0;
			Sheet sheet = Charges.getSheetAt(0);

			if (sheetIndex == 0) {
				String firstCellValue = dataFormatter.formatCellValue(sheet.getRow(0).getCell(0));
				if (!firstCellValue.equals("CHARGES")) {
					throw new RuntimeException("The file selected is not the correct CHARGES file. Please select the correct CHARGES file.");
				}
			}

			noOfColumns = sheet.getRow(1).getLastCellNum(); 

			for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
					chList.add(cellValue);
				}
			}
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		// filling excel data 
		List<Charges> chargeList = createchargeList(chList, noOfColumns);

		return chargeList;
	}

	private List<Charges> createchargeList(List<String> excelData, int noOfColumns) {

		ArrayList<Charges> chargeList = new ArrayList<>();

		int i = 0;
		do {
			Charges charges = new Charges();

			charges.setClassLevel(Integer.valueOf(excelData.get(i)));
			charges.setFlatRate(Float.valueOf(excelData.get(i + 1)));
			charges.setTwoClassRate(Float.valueOf(excelData.get(i + 2)));
			charges.setMultiClassRate(Float.valueOf(excelData.get(i + 3)));
			charges.setSiblingDiscount(Double.valueOf(excelData.get(i + 4)));

			chargeList.add(charges);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		return chargeList;
	}

	@Override
	public int saveChargeData(List<Charges> saveChargeList) {
		saveChargeList = chargeRepo.saveAll(saveChargeList);
		return saveChargeList.size();
	}

	@Override
	public List<Children> getChildDataAsList(MultipartFile file) {

		List<String> crList = new ArrayList<>();

		// format each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();
		int noOfColumns = 0;
		// create an work though Workbook
		try {
			Workbook ParentChild = WorkbookFactory.create(file.getInputStream());

			Sheet childSheet = ParentChild.getSheetAt(1);
			noOfColumns = childSheet.getRow(0).getLastCellNum();

			for (int rowNum = 1; rowNum <= childSheet.getLastRowNum(); rowNum++) {
				Row row = childSheet.getRow(rowNum);
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
					crList.add(cellValue);
				}
			}
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		// filling excel data - creating list as List<Children>
		List<Children> childList = createchildList(crList, noOfColumns);

		return childList;
	}

	private List<Children> createchildList(List<String> excelData, int noOfColumns) {

		ArrayList<Children> childList = new ArrayList<>();

		int i = noOfColumns;

		do {
			Children children = new Children();

			children.setChildID(Integer.valueOf(excelData.get(i)));
			children.setLastName(excelData.get(i + 1));
			children.setFirstName(excelData.get(i + 2));
			children.setBirthDate(excelData.get(i + 3));
			children.setAge(Integer.valueOf(excelData.get(i + 4)));
			children.setGrade(excelData.get(i + 5));
			children.setRegistrationDate(excelData.get(i + 9));
			children.setActivityID(excelData.get(i + 7));
			children.setStudioID(Integer.valueOf(excelData.get(i + 8)));
			children.setActivityLevel(Integer.valueOf(excelData.get(i + 6)));
			children.setParentID(Integer.valueOf(excelData.get(i+10)));
			String statusString = excelData.get(i + 11);
			boolean status = statusString.equalsIgnoreCase("TRUE");
			children.setStatus(status);

			childList.add(children);
			i = i + (noOfColumns);

		} while (i + 11 < excelData.size());
		return childList;
	}

	@Override
	public int saveChildData(List<Children> saveChildList) {
		saveChildList = childRepo.saveAll(saveChildList);
		return saveChildList.size();
	}

	@Override
	public List<Instructor> getInstructDataAsList(MultipartFile file) {

		List<String> inList = new ArrayList<>();

		// format each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		int noOfColumns = 0;

		// create an work though Workbook
		try (Workbook Instructor = WorkbookFactory.create(file.getInputStream())){
			Sheet sheet = Instructor.getSheetAt(0);

			String firstCellValue = dataFormatter.formatCellValue(sheet.getRow(0).getCell(0));
			if (!firstCellValue.equals("INSTRUCTOR")) {
				throw new RuntimeException("The file selected is not the correct INSTRUCTOR file. Please select the correct INSTRUCTOR file.");
			}

			noOfColumns = sheet.getRow(1).getLastCellNum();

			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
					inList.add(cellValue);
				}
			}
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		// filling excel data - creating list as List<Instructor>
		List<Instructor> instructList = createinstructList(inList, noOfColumns);

		return instructList;
	}

	private List<Instructor> createinstructList(List<String> excelData, int noOfColumns) {

		ArrayList<Instructor> instructList = new ArrayList<>();

		int i = noOfColumns;
		do {
			Instructor instruct = new Instructor();

			instruct.setInstructorID(Integer.valueOf(excelData.get(i)));
			instruct.setLastName(excelData.get(i + 1));
			instruct.setFirstName(excelData.get(i + 2));
			instruct.setEmail(excelData.get(i + 3));
			instruct.setInstructorPhoneNum(excelData.get(i + 4));
			instruct.setSpecialty(excelData.get(i + 5));
			instruct.setStudioA(excelData.get(i + 6));
			instruct.setStudioB(excelData.get(i + 7));

			instructList.add(instruct);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		return instructList;
	}

	@Override
	public int saveInstructorData(List<Instructor> saveInstructList) {
		saveInstructList = instructRepo.saveAll(saveInstructList);
		return saveInstructList.size();
	}

	// manager
	@Override
	public List<Manager> getManagerDataAsList() {

		List<String> list = new ArrayList<>();

		// format each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// create an work though Workbook
		try {
			Admin = WorkbookFactory.create(new File(ADMIN_FILE_PATH));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		Sheet sheet = Admin.getSheetAt(1);
		int noOfColumns = sheet.getRow(0).getLastCellNum();

		for (Row row : sheet) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				list.add(cellValue);
			}
		}

		// filling excel data - creating list as List<Manager>
		List<Manager> managerList = createmanagerList(list, noOfColumns);

		// closing the workbook
		try {
			Admin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return managerList;
	}

	private List<Manager> createmanagerList(List<String> excelData, int noOfColumns) {

		ArrayList<Manager> managerList = new ArrayList<>();

		int i = noOfColumns;
		do {
			Manager manager = new Manager();

			manager.setManagerID(excelData.get(i + 4));
			manager.setLastName(excelData.get(i));
			manager.setFirstName(excelData.get(i + 1));
			manager.setEmail(excelData.get(i + 2));
			manager.setManagerPhoneNum(excelData.get(i + 3));

			managerList.add(manager);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		return managerList;
	}

	@Override
	public int saveManagerData(List<Manager> saveManagerList) {
		saveManagerList = managerRepo.saveAll(saveManagerList);
		return saveManagerList.size();
	}

	// parent
	@Override
	public List<Parent> getParentDataAsList(MultipartFile file) {

		List<String> PClist = new ArrayList<>();

		// format each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		int noOfColumns = 0;

		// create and work though Workbook
		try  {
			Workbook ParentChild = WorkbookFactory.create(file.getInputStream());
			Sheet parentSheet = ParentChild.getSheetAt(0);

			int sheetIndex = 0;	
			if (sheetIndex == 0) {
				String firstCellValue = dataFormatter.formatCellValue(parentSheet.getRow(0).getCell(0));
				if (!firstCellValue.equals("PARENTCHILD")) {
					throw new RuntimeException("The file selected is not the correct PARENT/CHILD file. Please select the correct PARENT/CHILD file.");
				}
			}

			noOfColumns = parentSheet.getRow(1).getLastCellNum(); 

			for (int rowNum = 2; rowNum <= parentSheet.getLastRowNum(); rowNum++) {
				Row row = parentSheet.getRow(rowNum);
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
					PClist.add(cellValue);
				}
			}

		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		// filling excel data - creating list as List<Parent>
		List<Parent> parentList = createparentList(PClist, noOfColumns);

		return parentList;
	}

	private List<Parent> createparentList(List<String> excelData, int noOfColumns) {

		ArrayList<Parent> parentList = new ArrayList<>();

		int i = 0;
		do {
			Parent parent = new Parent();

			parent.setParentID(Integer.valueOf(excelData.get(i)));
			parent.setParent1Name(excelData.get(i + 1));
			parent.setParent1PhoneNum(excelData.get(i + 2));
			parent.setParent2Name(excelData.get(i + 3));
			parent.setParent2PhoneNum(excelData.get(i + 4));
			parent.setPrimaryEmail(excelData.get(i + 5));
			parent.setPrimaryAddress(excelData.get(i + 6));
			parent.seteContName(excelData.get(i + 7));
			parent.seteContNum(excelData.get(i + 8));
			parent.setBalance(0.0f);

			parentList.add(parent);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		return parentList;
	}

	@Override
	public int saveParentData(List<Parent> saveParentList) {
		saveParentList = parentRepo.saveAll(saveParentList);
		return saveParentList.size();
	}

	@Override
	public Optional<Parent> findById(int parentID) {
		return parentRepo.findById(parentID);
	}

	@Override
	public void save(Parent parent) {
		parentRepo.save(parent);
	}

	@Override
	public List<Parent> findAll() {
		return parentRepo.findAll();
	}

	@Override
	public void delete(int parentID) {
		parentRepo.deleteById(parentID);
	}
}