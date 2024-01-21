package edu.sru.thangiah.group2.fall2023registrationsystem.domain.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Admin;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTests {

    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin("rocco1", "Lad", "Rocco", "rocco@studio.com", "725-569-9001");
    }

    @Test
    public void testAdminIDGetterAndSetter() {
        assertEquals("rocco1", admin.getAdminID());
        admin.setAdminID("999");
        assertEquals("999", admin.getAdminID());
    }

    @Test
    public void testLastNameGetterAndSetter() {
        assertEquals("Lad", admin.getLastName());
        admin.setLastName("Smith");
        assertEquals("Smith", admin.getLastName());
    }

    @Test
    public void testFirstNameGetterAndSetter() {
        assertEquals("Rocco", admin.getFirstName());
        admin.setFirstName("Emilie");
        assertEquals("Emilie", admin.getFirstName());
    }

    @Test
    public void testEmailGetterAndSetter() {
        assertEquals("rocco@studio.com", admin.getEmail());
        admin.setEmail("admin@studio.com");
        assertEquals("admin@studio.com", admin.getEmail());
    }

    @Test
    public void testAdminPhoneNumGetterAndSetter() {
        assertEquals("725-569-9001", admin.getAdminPhoneNum());
        admin.setAdminPhoneNum("754-973-2823");
        assertEquals("754-973-2823", admin.getAdminPhoneNum());
    }

    @Test
    public void testPasswordGetterAndSetter() {
        admin.setPassword("root");
        assertEquals("root", admin.getPassword());
    }

    @Test
    public void testRoleGetterAndSetter() {
        admin.setRole("Admin");
        assertEquals("Admin", admin.getRole());
    }

    @Test
    public void testToStringMethod() {
        String expectedString = "Admin [userID = rocco1, lastName = Lad, firstName = Rocco, email = rocco@studio.com, phoneNum = 725-569-9001]";
        assertEquals(expectedString, admin.toString());
    }
}
