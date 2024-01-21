package edu.sru.thangiah.group2.fall2023registrationsystem.domain.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;

import static org.junit.jupiter.api.Assertions.*;

public class InstructorTest {

    private Instructor instructor;

    @BeforeEach
    public void setUp() {
        instructor = new Instructor(1001, "Smith", "Aurora", "asmith@studio.com", "789-789-7899", "Jazz", "2", "0");
    }

    @Test
    public void testInstructorIDGetterAndSetter() {
        assertEquals(1001, instructor.getInstructorID());
        instructor.setInstructorID(2002);
        assertEquals(2002, instructor.getInstructorID());
    }

    @Test
    public void testLastNameGetterAndSetter() {
        assertEquals("Smith", instructor.getLastName());
        instructor.setLastName("Gloat");
        assertEquals("Gloat", instructor.getLastName());
    }

    @Test
    public void testFirstNameGetterAndSetter() {
        assertEquals("Aurora", instructor.getFirstName());
        instructor.setFirstName("Joe");
        assertEquals("Joe", instructor.getFirstName());
    }

    @Test
    public void testEmailGetterAndSetter() {
        assertEquals("asmith@studio.com", instructor.getEmail());
        instructor.setEmail("joegloat@studio.com");
        assertEquals("joegloat@studio.com", instructor.getEmail());
    }

    @Test
    public void testInstructorPhoneNumGetterAndSetter() {
        assertEquals("789-789-7899", instructor.getInstructorPhoneNum());
        instructor.setInstructorPhoneNum("675-908-5555");
        assertEquals("675-908-5555", instructor.getInstructorPhoneNum());
    }

    @Test
    public void testSpecialtyGetterAndSetter() {
        assertEquals("Jazz", instructor.getSpecialty());
        instructor.setSpecialty("Acro");
        assertEquals("Acro", instructor.getSpecialty());
    }

    @Test
    public void testStudioAGetterAndSetter() {
        assertEquals("2", instructor.getStudioA());
        instructor.setStudioA("3");
        assertEquals("3", instructor.getStudioA());
    }

    @Test
    public void testStudioBGetterAndSetter() {
        assertEquals("0", instructor.getStudioB());
        instructor.setStudioB("4");
        assertEquals("4", instructor.getStudioB());
    }

    @Test
    public void testPasswordGetterAndSetter() {
        instructor.setPassword("1234");
        assertEquals("1234", instructor.getPassword());
    }

    @Test
    public void testRoleGetterAndSetter() {
        instructor.setRole("Instructor");
        assertEquals("Instructor", instructor.getRole());
    }
}
