package edu.sru.thangiah.group2.fall2023registrationsystem.service.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.sru.thangiah.group2.fall2023registrationsystem.service.ExportDataService;

import org.junit.jupiter.api.BeforeEach;
import java.io.File;


public class ExportDataServiceTest {

    private ExportDataService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new ExportDataService(); 
    }

    @Test
    public void testExportAMLDataToExcel() throws Exception {

        String expectedFileName = "AdminManagerLocation.xlsx"; 

       
        service.exportAMLDataToExcel();
        File outputFile = new File(expectedFileName);
        assertTrue(outputFile.exists());
    }

    @Test
    public void testExportInstructorDataToExcel() throws Exception {
        String expectedFileName = "Instructor.xlsx";
        service.exportInstructorDataToExcel();
        File outputFile = new File(expectedFileName);
        assertTrue(outputFile.exists());
    }

    @Test
    public void testExportActivityDataToExcel() throws Exception {
        String expectedFileName = "Activities.xlsx";
        service.exportActivityDataToExcel();
        File outputFile = new File(expectedFileName);
        assertTrue(outputFile.exists());
    }

    @Test
    public void testExportChargesDataToExcel() throws Exception {
        String expectedFileName = "Charges.xlsx"; 
        service.exportChargesDataToExcel();
        File outputFile = new File(expectedFileName);
        assertTrue(outputFile.exists());
    }

    @Test
    public void testExportParentChildDataToExcel() throws Exception {
        String expectedFileName = "ParentChild.xlsx"; 
        service.exportParentChildDataToExcel();
        File outputFile = new File(expectedFileName);
        assertTrue(outputFile.exists());
    }
}
