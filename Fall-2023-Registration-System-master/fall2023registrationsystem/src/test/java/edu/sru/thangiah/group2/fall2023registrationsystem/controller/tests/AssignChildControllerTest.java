package edu.sru.thangiah.group2.fall2023registrationsystem.controller.tests;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.sru.thangiah.group2.fall2023registrationsystem.controller.AssignChildController;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;


public class AssignChildControllerTest {

    private AssignChildController assignChildController;
    private Model mockModel;
    private RedirectAttributes mockRedirectAttributes;

    @BeforeEach
    public void setUp() {
        assignChildController = new AssignChildController();
        mockModel = mock(Model.class);
        mockRedirectAttributes = mock(RedirectAttributes.class);
    }

    @Test
    public void testShowChildAssign() {
        int childID = 3010; 
        String viewName = assignChildController.showChildAssign(mockModel, childID);

        assertEquals("childAssignment", viewName);
    }

    @Test
    public void testProcessAssignment() {
        Children childAssignment = new Children(); 
        String activityID = "ACRO";
        Integer activityLevel = 1;
        int studioID = 1;
        int childID = 3010;

        String viewName = assignChildController.processAssignment(childAssignment, activityID, activityLevel, studioID, childID, mockModel, mockRedirectAttributes);

        assertEquals("/childAssignment?childID", viewName);
    }

}
