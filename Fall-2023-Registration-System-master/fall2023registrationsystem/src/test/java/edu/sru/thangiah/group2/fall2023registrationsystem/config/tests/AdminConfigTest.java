package edu.sru.thangiah.group2.fall2023registrationsystem.config.tests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.CommandLineRunner;

import edu.sru.thangiah.group2.fall2023registrationsystem.config.AdminConfig;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Admin;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.AdminRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class AdminConfigTest {

    @InjectMocks
    private AdminConfig adminConfig;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadData() {
        when(adminRepository.findByEmail("admin@studio.com")).thenReturn(null);
        when(userRepository.findByEmail("admin@studio.com")).thenReturn(null);
        when(passwordEncoder.encode("root")).thenReturn("encodedPassword");

        CommandLineRunner runner = adminConfig.loadData();

        assertNotNull(runner);

        //runner.run();

        verify(adminRepository, times(1)).save(any(Admin.class));
        //verify(userRepository, times(1)).save(any(User.class));
    }


}
