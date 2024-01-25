package edu.sru.thangiah.group2.fall2023registrationsystem.controller.tests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import edu.sru.thangiah.group2.fall2023registrationsystem.controller.LogonController;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;

public class LogonControllerTest {

    private LogonController logonController;
    private Model mockModel;
    private BindingResult mockBindingResult;
    private HttpSession mockSession;

    @BeforeEach
    public void setUp() {
        logonController = new LogonController();
        mockModel = mock(Model.class);
        mockBindingResult = mock(BindingResult.class);
        mockSession = mock(HttpSession.class);
    }

    @Test
    public void testViewHomePage() {
        String viewName = logonController.viewHomePage();

        assertEquals("/index", viewName);
    }

    @Test
    public void testViewContactPage() {
        String viewName = logonController.viewContactPage();

        assertEquals("/contactUs", viewName);
    }

    @Test
    public void testShowRegistrationForm() {
        String viewName = logonController.showRegistrationForm(mockModel);

        assertEquals("/register", viewName);
    }

    @Test
    public void testProcessRegister() {
        User user = new User(); // assuming User is a domain/model class
        // Setup and Execution
        String viewName = logonController.processRegister(user, mockBindingResult, mockModel);

        // Assertion
        assertEquals("signup_form", viewName);
    }

    @Test
    public void testEmailExistsInTable() {
        String tableName = "parent";
        String email = "clara@gmail.com";

        boolean result = logonController.emailExistsInTable(tableName, email);

    }

    @Test
    public void testInstructorDashboard() {
        String viewName = logonController.instructorDashboard();

        assertEquals("instructor", viewName);
    }

    @Test
    public void testRedirectToDashboard() {

        //String viewName = logonController.redirectToDashboard(mockModel, mockSession);

        //assertEquals("dashboards", viewName);
    }

    @Test
    public void testLoginSuccess() {
    	
    	Model mockModel = mock(Model.class);
        RedirectAttributes mockRedirectAttributes = mock(RedirectAttributes.class);

        String viewName = logonController.loginSuccess(mockModel, mockRedirectAttributes);

        assertEquals("loginSuccess", viewName);
    }

}
