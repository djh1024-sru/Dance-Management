package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatabaseBackupService {

	public static Map<String, String> backup() {
		Map<String, String> response = new HashMap<>();

		List<String> validTables = Arrays.asList("activities", "activities_charges", "admin", "charges", "child",
				"instructor", "instructor_location", "location", "manager", "parent", "users");

		try {

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_system", "root", "root");

			DatabaseMetaData meta = conn.getMetaData();
			ResultSet tables = meta.getTables(null, null, "%", new String[] { "TABLE" });

			Workbook workbook = new XSSFWorkbook();

			while (tables.next()) {
				String tableName = tables.getString(3);

				if (!validTables.contains(tableName)) {
					continue;
				}

				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);

				Sheet sheet = workbook.createSheet(tableName);
				Row headerRow = sheet.createRow(0);

				ResultSetMetaData rsmd = resultSet.getMetaData();
				int columnCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					Cell cell = headerRow.createCell(i - 1);
					cell.setCellValue(rsmd.getColumnName(i));
				}

				int rowNum = 1;
				while (resultSet.next()) {
					Row row = sheet.createRow(rowNum++);
					for (int i = 1; i <= columnCount; i++) {
						Cell cell = row.createCell(i - 1);
						cell.setCellValue(resultSet.getString(i));
					}
				}
			}

			String folderName = "Backups";

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String formattedDate = sdf.format(new Date());

			String fileName = "DatabaseBackup_" + formattedDate + ".xlsx";

			File directory = new File(folderName);
			if (!directory.exists()) {
				directory.mkdir();
			}

			FileOutputStream fileOut = new FileOutputStream(new File(directory, fileName));
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

			response.put("status", "success");
			response.put("message", "Database has been backed up.");

		} catch (Exception e) {

			response.put("status", "error");
			response.put("message", e.getMessage());
		}

		return response;
	}

	public static void main(String[] args) {
		Map<String, String> result = backup();
		System.out.println(result.get("message"));
	}
}