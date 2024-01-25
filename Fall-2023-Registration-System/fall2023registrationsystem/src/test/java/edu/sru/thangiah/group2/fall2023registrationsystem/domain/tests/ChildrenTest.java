package edu.sru.thangiah.group2.fall2023registrationsystem.domain.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;

import static org.junit.jupiter.api.Assertions.*;

public class ChildrenTest {

    private Children child;

    @BeforeEach
    public void setUp() {
        child = new Children(3010, "Edwards", "Isabella", "8/20/19", 4, "Preschool", "9/14/23", "ACRO", 1, 1, true, 2006);
    }

    @Test
    public void testChildIDGetterAndSetter() {
        assertEquals(3010, child.getChildID());
        child.setChildID(3011);
        assertEquals(3011, child.getChildID());
    }

    @Test
    public void testLastNameGetterAndSetter() {
        assertEquals("Edwards", child.getLastName());
        child.setLastName("Snyder");
        assertEquals("Snyder", child.getLastName());
    }

    @Test
    public void testFirstNameGetterAndSetter() {
        assertEquals("Isabella", child.getFirstName());
        child.setFirstName("Emilie");
        assertEquals("Emilie", child.getFirstName());
    }

    @Test
    public void testBirthDateGetterAndSetter() {
        assertEquals("8/20/19", child.getBirthDate());
        child.setBirthDate("9/22/20");
        assertEquals("9/22/20", child.getBirthDate());
    }

    @Test
    public void testAgeGetterAndSetter() {
        assertEquals(4, child.getAge());
        child.setAge(11);
        assertEquals(11, child.getAge());
    }

    @Test
    public void testGradeGetterAndSetter() {
        assertEquals("Preschool", child.getGrade());
        child.setGrade("Grade 6");
        assertEquals("Grade 6", child.getGrade());
    }

    @Test
    public void testRegistrationDateGetterAndSetter() {
        assertEquals("9/14/23", child.getRegistrationDate());
        child.setRegistrationDate("10/14/23");
        assertEquals("10/14/23", child.getRegistrationDate());
    }

    @Test
    public void testActivityIDGetterAndSetter() {
        assertEquals("ACRO", child.getActivityID());
        child.setActivityID("BALLET");
        assertEquals("BALLET", child.getActivityID());
    }

    @Test
    public void testStudioIDGetterAndSetter() {
        assertEquals(1, child.getStudioID());
        child.setStudioID(2);
        assertEquals(2, child.getStudioID());
    }

    @Test
    public void testActivityLevelGetterAndSetter() {
        assertEquals(1, child.getActivityLevel());
        child.setActivityLevel(2);
        assertEquals(2, child.getActivityLevel());
    }

    @Test
    public void testStatusGetterAndSetter() {
        assertTrue(child.getStatus());
        child.setStatus(false);
        assertFalse(child.getStatus());
    }

    @Test
    public void testParentIDGetterAndSetter() {
        assertEquals(2006, child.getParentID());
        child.setParentID(2007);
        assertEquals(2007, child.getParentID());
    }
}
