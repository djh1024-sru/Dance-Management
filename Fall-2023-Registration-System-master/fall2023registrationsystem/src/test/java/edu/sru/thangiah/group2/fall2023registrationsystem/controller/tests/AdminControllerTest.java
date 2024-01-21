package edu.sru.thangiah.group2.fall2023registrationsystem.controller.tests;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import edu.sru.thangiah.group2.fall2023registrationsystem.controller.AdminController;

import org.springframework.http.ResponseEntity;


public class AdminControllerTest {

    private AdminController adminController;
    private Model mockModel;

    @BeforeEach
    public void setUp() {
        
        adminController = new AdminController();
        mockModel = mock(Model.class);
    }

    @Test
    public void testAdminDashboard() {
       
        String viewName = adminController.adminDashboard(mockModel);

        assertEquals("admin", viewName);
        
    }

    @Test
    public void testCreateAdminPage() {
       
        String viewName = adminController.createAdminPage(mockModel);

        assertEquals("createUser-Admin", viewName);
      
    }

    @Test
    public void testUserCreationSuccess() {
       
        String viewName = adminController.UserCreationSuccess();

        assertEquals("UserCreationSuccess", viewName);

    }

    @Test
    public void testAdminExport() {
        
        String viewName = adminController.adminExport();

        assertEquals("adminExport", viewName);
       
    }

    @Test
    public void testDeleteUser() {
        
        ResponseEntity<String> response = adminController.deleteUser("testEmail@example.com");

        //assertEquals(HttpStatus.EXPECTED_STATUS, response.getStatusCode());
        assertEquals("User deleted successfully", response.getBody());
      
    }

    @Test
    public void testUserDeletedSuccess() {

        String viewName = adminController.userDeletedSuccess();

        assertEquals("user-deleted-success", viewName);

    }

    @Test
    public void testResetPassword() {

        ResponseEntity<String> response = adminController.resetPassword("testEmail@example.com");

        //assertEquals(HttpStatus.EXPECTED_STATUS, response.getStatusCode());
        assertEquals("User not found", response.getBody());

    }
}
