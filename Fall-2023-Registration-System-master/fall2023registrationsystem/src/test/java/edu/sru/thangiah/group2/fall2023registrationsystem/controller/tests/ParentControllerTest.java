package edu.sru.thangiah.group2.fall2023registrationsystem.controller.tests;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import edu.sru.thangiah.group2.fall2023registrationsystem.controller.ParentController;

public class ParentControllerTest {

    private ParentController parentController;
    private Model mockModel;

    @BeforeEach
    public void setUp() {
        parentController = new ParentController();
        mockModel = mock(Model.class);
    }

    @Test
    public void testParentDashboard() {
        String viewName = parentController.parentDashboard();

        assertEquals("parent", viewName);
    }

    @Test
    public void testSelectChild() {
        String viewName = parentController.selectChild(mockModel);

        assertEquals("selectChild", viewName);
    }

    @Test
    public void testChildActivities() {
        int childID = 3012;
        String viewName = parentController.childActivities(mockModel, childID);
        assertEquals("childActivities", viewName);
    }
}
