package edu.sru.thangiah.group2.fall2023registrationsystem.config.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.sru.thangiah.group2.fall2023registrationsystem.config.WebSecurityConfig;

import org.springframework.security.core.userdetails.UserDetailsService;


public class WebSecurityConfigTest {

    @InjectMocks
    private WebSecurityConfig webSecurityConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserDetailsService() {
        UserDetailsService userDetailsService = webSecurityConfig.userDetailsService();

        assertNotNull(userDetailsService);
    }

    @Test
    public void testPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = webSecurityConfig.passwordEncoder();

        assertNotNull(passwordEncoder);
    }

    @Test
    public void testAuthenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = webSecurityConfig.authenticationProvider();

        assertNotNull(authenticationProvider);
        
    }


}
