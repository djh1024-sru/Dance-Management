package edu.sru.thangiah.group2.fall2023registrationsystem.security.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.sru.thangiah.group2.fall2023registrationsystem.security.PasswordConstraintValidator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PasswordConstraintValidatorTest {

    private PasswordConstraintValidator validator;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        validator = new PasswordConstraintValidator();
        
    }

    @Test
    public void testIsValidWithValidPassword() {
        String validPassword = "ValidPass123!";
        //assertTrue(validator.isValid(validPassword));
    }

    @Test
    public void testIsValidWithInvalidPassword() {
        String invalidPassword = "shrt";
        //assertFalse(validator.isValid(invalidPassword));

    }

    
}
