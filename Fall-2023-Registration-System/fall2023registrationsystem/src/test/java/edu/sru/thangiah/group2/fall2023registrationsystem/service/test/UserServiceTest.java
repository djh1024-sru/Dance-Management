package edu.sru.thangiah.group2.fall2023registrationsystem.service.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Admin;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Manager;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.UserRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.UserService;

import org.junit.jupiter.api.BeforeEach;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testPopulateUserRepository() {

    }

    @Test
    public void testGetCurrentUser() {
        //Mockito.when(userRepository.findCurrentUser()).thenReturn(new User());
        User result = userService.getCurrentUser();
        assertNotNull(result);
    }

    @Test
    public void testUpdateAdmin() {
        Admin admin = new Admin();
        try {
            userService.updateAdmin(admin);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
	
	@Test
    public void testUpdateManager() {
        Manager manager = new Manager();
        try {
            userService.updateManager(manager);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
	
		@Test
    public void testUpdateInstructor() {
        Instructor instructor = new Instructor();
        try {
            userService.updateInstructor(instructor);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
	
			@Test
    public void testUpdateParent() {
        Parent parent = new Parent();
        try {
            userService.updateParent(parent);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
	
				@Test
    public void testUpdateUser() {
        User user = new User();
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    

    @Test
    public void testUpdatePasswordForAllRoles() {
        String email = "test@example.com";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        try {
            userService.updatePasswordForAllRoles(email, oldPassword, newPassword);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testFindByEmailWithValidEmail() {
        String validEmail = "daphne@gmail.com";
        Mockito.when(userRepository.findByEmail(validEmail)).thenReturn(new User());
        User result = userService.findByEmail(validEmail);
        assertNotNull(result);
    }

    @Test
    public void testFindByEmailWithInvalidEmail() {
        String invalidEmail = "fred@hotmail.com";
        Mockito.when(userRepository.findByEmail(invalidEmail)).thenReturn(null);
        User result = userService.findByEmail(invalidEmail);
        assertNull(result);
    }

}
