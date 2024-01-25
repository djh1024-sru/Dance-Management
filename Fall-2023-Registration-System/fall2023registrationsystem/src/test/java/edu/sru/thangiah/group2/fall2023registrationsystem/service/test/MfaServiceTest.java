package edu.sru.thangiah.group2.fall2023registrationsystem.service.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;

import edu.sru.thangiah.group2.fall2023registrationsystem.service.MfaService;

import org.junit.jupiter.api.BeforeEach;

public class MfaServiceTest {

    @InjectMocks
    private MfaService mfaService;

    @Mock
    private TimeBasedOneTimePasswordUtil timeBasedOneTimePasswordUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCheckWithCorrectCode() {
        String hexKey = "any_hex_key";
        String code = "any_code";
        
        boolean result = mfaService.check(hexKey, code);
        assertTrue(result);
    }

    @Test
    public void testCheckWithIncorrectCode() {
        String hexKey = "any_hex_key";
        String code = "any_code";
       
        boolean result = mfaService.check(hexKey, code);
        assertFalse(result);
    }

  
}
