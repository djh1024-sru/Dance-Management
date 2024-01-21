package edu.sru.thangiah.group2.fall2023registrationsystem.domain.tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Manager;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {

    private Manager manager;

    @BeforeEach
    public void setUp() {
        manager = new Manager("ejs1027", "Snyder", "Emilie", "ejs1027@studio.com", "333-333-4102");
    }

    @Test
    public void testManagerIDGetterAndSetter() {
        assertEquals("ejs1027", manager.getManagerID());
        manager.setManagerID("ams1099");
        assertEquals("ams1099", manager.getManagerID());
    }

    @Test
    public void testLastNameGetterAndSetter() {
        assertEquals("Snyder", manager.getLastName());
        manager.setLastName("Sutch");
        assertEquals("Sutch", manager.getLastName());
    }

    @Test
    public void testFirstNameGetterAndSetter() {
        assertEquals("Emilie", manager.getFirstName());
        manager.setFirstName("Alexis");
        assertEquals("Alexis", manager.getFirstName());
    }

    @Test
    public void testEmailGetterAndSetter() {
        assertEquals("ejs1027@studio.com", manager.getEmail());
        manager.setEmail("ams1099@studio.com");
        assertEquals("ams1099@studio.com", manager.getEmail());
    }

    @Test
    public void testManagerPhoneNumGetterAndSetter() {
        assertEquals("333-333-4102", manager.getManagerPhoneNum());
        manager.setManagerPhoneNum("999-154-1512");
        assertEquals("999-154-1512", manager.getManagerPhoneNum());
    }

    @Test
    public void testPasswordGetterAndSetter() {
        manager.setPassword("1234");
        assertEquals("1234", manager.getPassword());
    }

    @Test
    public void testRoleGetterAndSetter() {
        manager.setRole("Manager");
        assertEquals("Manager", manager.getRole());
    }

    @Test
    public void testToStringMethod() {
        String expectedString = "Manager [userID = ejs1027, lastName = Snyder, firstName = Emilie, email = ejs1027@studio.com, phoneNum = 333-333-4102]";
        assertEquals(expectedString, manager.toString());
    }

}
