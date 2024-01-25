package edu.sru.thangiah.group2.fall2023registrationsystem.domain.tests;

import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.ChildId;

import static org.junit.jupiter.api.Assertions.*;

public class ChildIdTest {

    @Test
    public void testConstructorAndGetters() {
        int expectedChildID = 3010;
        String expectedActivityID = "ACRO";
        Integer expectedActivityLevel = 1;

        ChildId childId = new ChildId(expectedChildID, expectedActivityID, expectedActivityLevel);

        assertEquals(expectedChildID, childId.getChildID());
        assertEquals(expectedActivityID, childId.getActivityID());
        assertEquals(expectedActivityLevel, childId.getActivityLevel());
    }
}
