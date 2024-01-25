package edu.sru.thangiah.group2.fall2023registrationsystem.domain.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Location;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    private Location location;

    @BeforeEach
    public void setUp() {
        // Note: For simplicity, 'activities' and 'instructor' are set to null. In actual use, they should be valid objects.
        location = new Location(1, "Slippery Rock", "130 Arrowhead Dr", " ", "Slippery Rock", "PA", "16057", "724-406-2659");
    }

    @Test
    public void testStudioIDGetterAndSetter() {
        assertEquals(1, location.getStudioID());
        location.setStudioID(2);
        assertEquals(2, location.getStudioID());
    }

    @Test
    public void testLocationNameGetterAndSetter() {
        assertEquals("Slippery Rock", location.getLocationName());
        location.setLocationName("Mercer");
        assertEquals("Mercer", location.getLocationName());
    }

    @Test
    public void testAddressGetterAndSetter() {
        assertEquals("130 Arrowhead Dr", location.getAddress());
        location.setAddress("145 Cranberry Rd");
        assertEquals("145 Cranberry Rd", location.getAddress());
    }

    @Test
    public void testAddress2GetterAndSetter() {
        assertEquals(" ", location.getAddress2());
        location.setAddress2("Suite 1");
        assertEquals("Suite 1", location.getAddress2());
    }

    @Test
    public void testCityGetterAndSetter() {
        assertEquals("Slippery Rock", location.getCity());
        location.setCity("Mercer");
        assertEquals("Mercer", location.getCity());
    }

    @Test
    public void testStateGetterAndSetter() {
        assertEquals("PA", location.getState());
        location.setState("WV");
        assertEquals("WV", location.getState());
    }

    @Test
    public void testZipGetterAndSetter() {
        assertEquals("16057", location.getZip());
        location.setZip("16078");
        assertEquals("16078", location.getZip());
    }

    @Test
    public void testPhoneNumGetterAndSetter() {
        assertEquals("724-406-2659", location.getPhoneNum());
        location.setPhoneNum("154-782-7894");
        assertEquals("154-782-7894", location.getPhoneNum());
    }
}
