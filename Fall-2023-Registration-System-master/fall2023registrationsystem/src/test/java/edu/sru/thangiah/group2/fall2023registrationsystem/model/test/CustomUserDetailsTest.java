package edu.sru.thangiah.group2.fall2023registrationsystem.model.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

import edu.sru.thangiah.group2.fall2023registrationsystem.model.CustomUserDetails;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;

public class CustomUserDetailsTest {

    @Mock
    private User mockUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConstructorWithUser() {
        CustomUserDetails userDetails = new CustomUserDetails(mockUser);
        assertNotNull(userDetails);
    }

    @Test
    public void testConstructorWithUserAndAuthorities() {
        Collection<? extends GrantedAuthority> authorities = null; 
        CustomUserDetails userDetails = new CustomUserDetails(mockUser, authorities);
        assertNotNull(userDetails);
    }

    @Test
    public void testGetAuthorities() {
        CustomUserDetails userDetails = new CustomUserDetails(mockUser);
        assertNotNull(userDetails.getAuthorities());
    }

    @Test
    public void testGetPassword() {
        CustomUserDetails userDetails = new CustomUserDetails(mockUser);
        assertEquals("root", userDetails.getPassword());
    }

    @Test
    public void testGetUsername() {
        CustomUserDetails userDetails = new CustomUserDetails(mockUser);
        assertEquals("rocco1", userDetails.getUsername());
    }

    @Test
    public void testIsAccountNonExpired() {
        CustomUserDetails userDetails = new CustomUserDetails(mockUser);
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        CustomUserDetails userDetails = new CustomUserDetails(mockUser);
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        CustomUserDetails userDetails = new CustomUserDetails(mockUser);
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        CustomUserDetails userDetails = new CustomUserDetails(mockUser);
        assertTrue(userDetails.isEnabled());
    }

    @Test
    public void testGetFullName() {
        CustomUserDetails userDetails = new CustomUserDetails(mockUser);
        assertEquals("Rocco", userDetails.getFullName());
    }

}
