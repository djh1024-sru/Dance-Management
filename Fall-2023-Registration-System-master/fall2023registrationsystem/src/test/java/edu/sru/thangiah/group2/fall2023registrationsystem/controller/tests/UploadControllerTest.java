package edu.sru.thangiah.group2.fall2023registrationsystem.controller.tests;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.sru.thangiah.group2.fall2023registrationsystem.controller.UploadController;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class UploadControllerTest {

    private UploadController uploadController;
    private MultipartFile mockFile;
    private RedirectAttributes mockRedirectAttributes;

    @BeforeEach
    public void setUp() {
        uploadController = new UploadController();
        mockFile = mock(MultipartFile.class);
        mockRedirectAttributes = mock(RedirectAttributes.class);
    }

    @Test
    public void testUploadActivityForm() {
        String viewName = uploadController.uploadActivityForm();

        assertEquals("uploadActivity", viewName);
    }

    @Test
    public void testUploadAdminForm() {
        String viewName = uploadController.uploadAdminForm();

        assertEquals("uploadAdmin", viewName);
    }


    @Test
    public void testUploadFile() throws IOException, InvalidFormatException {
        when(mockFile.isEmpty()).thenReturn(false);

        String viewName = uploadController.uploadFile(mockFile, mockRedirectAttributes);

        assertNotNull(viewName);

    }

}
