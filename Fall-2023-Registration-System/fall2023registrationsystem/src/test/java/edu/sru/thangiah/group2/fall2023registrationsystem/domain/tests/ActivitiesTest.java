package edu.sru.thangiah.group2.fall2023registrationsystem.domain.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Activities;

import static org.junit.jupiter.api.Assertions.*;

public class ActivitiesTest {

    private Activities activity;

    @BeforeEach
    public void setUp() {
        //activity = new Activities("ACRO", "Slippery Rock", "Saturday", "10:30", "11:15", "Intermediate", null);
    }

    @Test
    public void testActivityIDGetterAndSetter() {
        assertEquals("ACRO", activity.getActivityID());
        activity.setActivityID("BALLET");
        assertEquals("BALLET", activity.getActivityID());
    }

    @Test
    public void testLocationNameGetterAndSetter() {
        assertEquals("Slippery Rock", activity.getLocationName());
        activity.setLocationName("Sharon");
        assertEquals("Sharon", activity.getLocationName());
    }

    @Test
    public void testWeekDayGetterAndSetter() {
        assertEquals("Saturday", activity.getWeekDay());
        activity.setWeekDay("Tuesday");
        assertEquals("Tuesday", activity.getWeekDay());
    }
/*
    @Test
    public void testStartTimeGetterAndSetter() {
        assertEquals("10:30", activity.getStartTime());
        activity.setStartTime("09:00");
        assertEquals("09:00", activity.getStartTime());
    }

    @Test
    public void testEndTimeGetterAndSetter() {
        assertEquals("11:15", activity.getEndTime());
        activity.setEndTime("11:00");
        assertEquals("11:00", activity.getEndTime());
    }*/

    @Test
    public void testActivityLevelGetterAndSetter() {
        assertEquals("Intermediate", activity.getActivityLevel());
        activity.setActivityLevel("Advanced");
        assertEquals("Advanced", activity.getActivityLevel());
    }

    @Test
    public void testInstructorIDGetterAndSetter() {
        assertEquals(1007, activity.getInstructorID());
        activity.setInstructorID(1008);
        assertEquals(1008, activity.getInstructorID());
    }

    
}
