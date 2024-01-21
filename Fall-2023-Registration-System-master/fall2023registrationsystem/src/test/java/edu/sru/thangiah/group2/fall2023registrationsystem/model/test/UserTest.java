package edu.sru.thangiah.group2.fall2023registrationsystem.model.test;

import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User(2001, "daphne@gmail.com", "1234", "Daphne", "Turner", "ROLE_USER", true);
        assertNotNull(user);
    }

    @Test
    public void testGetAndSetId() {
        User user = new User();
        user.setId(2001);
        assertEquals(2001, user.getId());
    }

    @Test
    public void testGetAndSetEmail() {
        User user = new User();
        user.setEmail("daphne@gmail.com");
        assertEquals("daphne@gmail.com", user.getEmail());
    }

    @Test
    public void testGetAndSetPassword() {
        User user = new User();
        user.setPassword("1234");
        assertEquals("1234", user.getPassword());
    }

    @Test
    public void testGetAndSetFirstName() {
        User user = new User();
        user.setFirstName("Daphne");
        assertEquals("Daphne", user.getFirstName());
    }

    @Test
    public void testGetAndSetLastName() {
        User user = new User();
        user.setLastName("Turner");
        assertEquals("Turner", user.getLastName());
    }

    @Test
    public void testGetAndSetFirstLogin() {
        User user = new User();
        user.setFirstLogin(true);
        assertTrue(user.isFirstLogin());
    }

    @Test
    public void testGetAndSetRole() {
        User user = new User();
        user.setRole("ROLE_USER");
        assertEquals("ROLE_USER", user.getRole());
    }
}
