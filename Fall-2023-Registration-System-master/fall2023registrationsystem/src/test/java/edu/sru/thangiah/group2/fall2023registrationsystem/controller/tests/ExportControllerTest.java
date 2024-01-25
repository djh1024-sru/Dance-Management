package edu.sru.thangiah.group2.fall2023registrationsystem.controller.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.sru.thangiah.group2.fall2023registrationsystem.controller.ExportController;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.ExportDataService;

public class ExportControllerTest {

    @InjectMocks
    private ExportController exportController;

    @Mock
    private ExportDataService exportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExportData() {
        String result = exportController.exportData();

        assertNotNull(result);

    }

    @Test
    public void testExportInstructorData() {

        String result = exportController.exportInstructorData();

        assertNotNull(result);
    }

    @Test
    public void testExportChargesData() {

        String result = exportController.exportChargesData();

        assertNotNull(result);
    }

    @Test
    public void testExportParentChildData() {

        String result = exportController.exportParentChildData();

        assertNotNull(result);
    }

    @Test
    public void testExportActivityData() {

        String result = exportController.exportActivityData();

        assertNotNull(result);
    }
}
