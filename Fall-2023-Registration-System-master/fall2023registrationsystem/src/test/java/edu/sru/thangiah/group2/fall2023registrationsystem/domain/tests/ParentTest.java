package edu.sru.thangiah.group2.fall2023registrationsystem.domain.tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;

import static org.junit.jupiter.api.Assertions.*;

public class ParentTest {

    private Parent parent;

    @BeforeEach
    public void setUp() {
       
        parent = new Parent(2001, "Daphne Turner", "123-123-1234", "Felix Turner", "123-024-9600", "daphne@gmail.com", "1 Sugar Ln, Slippery Rock PA 16057", "Daphne Turner", "123-123-1234", 100.0f);
    }

    @Test
    public void testParentIDGetterAndSetter() {
        assertEquals(2001, parent.getParentID());
        parent.setParentID(2002);
        assertEquals(2002, parent.getParentID());
    }

    @Test
    public void testParent1NameGetterAndSetter() {
        assertEquals("Daphne Turner", parent.getParent1Name());
        parent.setParent1Name("Alice Chester");
        assertEquals("Alice Chester", parent.getParent1Name());
    }

    @Test
    public void testParent1PhoneNumGetterAndSetter() {
        assertEquals("123-123-1234", parent.getParent1PhoneNum());
        parent.setParent1PhoneNum("123-345-6789");
        assertEquals("123-345-6789", parent.getParent1PhoneNum());
    }

    @Test
    public void testParent2NameGetterAndSetter() {
        assertEquals("Felix Turner", parent.getParent2Name());
        parent.setParent2Name("Atticus Chester");
        assertEquals("Atticus Chester", parent.getParent2Name());
    }

    @Test
    public void testParent2PhoneNumGetterAndSetter() {
        assertEquals("123-024-9600", parent.getParent2PhoneNum());
        parent.setParent2PhoneNum("789-521-5236");
        assertEquals("789-521-5236", parent.getParent2PhoneNum());
    }

    @Test
    public void testEmailGetterAndSetter() {
        assertEquals("daphne@gmail.com", parent.getPrimaryEmail());
        parent.setPrimaryEmail("alice@yahoo.com");
        assertEquals("alice@yahoo.com", parent.getPrimaryEmail());
    }

    @Test
    public void testPrimaryAddressGetterAndSetter() {
        assertEquals("1 Sugar Ln, Slippery Rock PA 16057", parent.getPrimaryAddress());
        parent.setPrimaryAddress("2 Splendid Cir, Grove City Pa 16127");
        assertEquals("2 Splendid Cir, Grove City Pa 16127", parent.getPrimaryAddress());
    }

    @Test
    public void testEContNameGetterAndSetter() {
        assertEquals("Daphne Turner", parent.geteContName());
        parent.seteContName("Alice Chester");
        assertEquals("Alice Chester", parent.geteContName());
    }

    @Test
    public void testEContNumGetterAndSetter() {
        assertEquals("123-123-1234", parent.geteContName());
        parent.seteContNum("123-345-6789");
        assertEquals("123-345-6789", parent.geteContName());
    }

    @Test
    public void testPasswordGetterAndSetter() {
        parent.setPassword("1234");
        assertEquals("1234", parent.getPassword());
    }

    @Test
    public void testRoleGetterAndSetter() {
        parent.setRole("Parent");
        assertEquals("Parent", parent.getRole());
    }

    @Test
    public void testBalanceGetterAndSetter() {
        assertEquals(100.0f, parent.getBalance());
        parent.setBalance(200.0f);
        assertEquals(200.0f, parent.getBalance());
    }
}
