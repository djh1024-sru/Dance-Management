package edu.sru.thangiah.group2.fall2023registrationsystem.domain.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Charges;

import static org.junit.jupiter.api.Assertions.*;

public class ChargesTest {

    private Charges charges;

    @BeforeEach
    public void setUp() {
        charges = new Charges(1, 3.0f, 5.0f, 8.0f, 0.1);
    }

    @Test
    public void testClassLevelGetterAndSetter() {
        assertEquals(1, charges.getClassLevel());
        charges.setClassLevel(2);
        assertEquals(2, charges.getClassLevel());
    }

    @Test
    public void testFlatRateGetterAndSetter() {
        assertEquals(3.0f, charges.getFlatRate());
        charges.setFlatRate(110.0f);
        assertEquals(110.0f, charges.getFlatRate());
    }

    @Test
    public void testTwoClassRateGetterAndSetter() {
        assertEquals(5.0f, charges.getTwoClassRate());
        charges.setTwoClassRate(190.0f);
        assertEquals(190.0f, charges.getTwoClassRate());
    }

    @Test
    public void testMultiClassRateGetterAndSetter() {
        assertEquals(8.0f, charges.getMultiClassRate());
        charges.setMultiClassRate(260.0f);
        assertEquals(260.0f, charges.getMultiClassRate());
    }

    @Test
    public void testSiblingDiscountGetterAndSetter() {
        assertEquals(0.1, charges.getSiblingDiscount());
        charges.setSiblingDiscount(0.20);
        assertEquals(0.20, charges.getSiblingDiscount());
    }

    @Test
    public void testToStringMethod() {
        String expectedString = "Charges [classLevel = 1, flatRate = 3.0, twoClassRate = 5.0, multiClassRate = 8.0, siblingDiscount = 0.1]";
        assertEquals(expectedString, charges.toString());
    }
}
