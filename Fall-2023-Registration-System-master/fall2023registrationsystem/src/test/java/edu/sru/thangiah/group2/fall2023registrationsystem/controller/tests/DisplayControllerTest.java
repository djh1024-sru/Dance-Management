package edu.sru.thangiah.group2.fall2023registrationsystem.controller.tests;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import edu.sru.thangiah.group2.fall2023registrationsystem.controller.DisplayController;


public class DisplayControllerTest {

    private DisplayController displayController;

    @BeforeEach
    public void setUp() {
        displayController = new DisplayController();
    }

    @Test
    public void testGetAllActivitiesAdmin() {

        ModelAndView modelAndView = displayController.getAllActivitiesAdmin();

        assertNotNull(modelAndView);
        assertEquals("/schedule", modelAndView.getViewName());
 
    }

    @Test
    public void testGetAllActivitiesManager() {

        ModelAndView modelAndView = displayController.getAllActivitiesManager();

        assertNotNull(modelAndView);
        assertEquals("/manager/schedule", modelAndView.getViewName());
    }

    @Test
    public void testGetAllChildrenManager() {
        ModelAndView modelAndView = displayController.getAllChildrenManager();

        assertNotNull(modelAndView);
        assertEquals("managerClassLists", modelAndView.getViewName());

    }

    @Test
    public void testGetAllChildrenInstructor() {

        ModelAndView modelAndView = displayController.getAllChildrenInstructor();

        assertNotNull(modelAndView);
        assertEquals("classLists", modelAndView.getViewName());
    }

    @Test
    public void testGetParentBalance() {
        ModelAndView modelAndView = displayController.getParentBalance();

        assertNotNull(modelAndView);
        assertEquals("parentBalance", modelAndView.getViewName());

    }

}
