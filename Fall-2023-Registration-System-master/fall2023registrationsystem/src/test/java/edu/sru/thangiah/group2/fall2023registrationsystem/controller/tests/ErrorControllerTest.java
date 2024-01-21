package edu.sru.thangiah.group2.fall2023registrationsystem.controller.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.controller.ErrorController;

public class ErrorControllerTest {

    private ErrorController errorController;

    @BeforeEach
    public void setUp() {
        errorController = new ErrorController();
    }

    @Test
    public void testHandle403() {
        String viewName = errorController.handle403();

        assertEquals("403.html", viewName);
    }

}
