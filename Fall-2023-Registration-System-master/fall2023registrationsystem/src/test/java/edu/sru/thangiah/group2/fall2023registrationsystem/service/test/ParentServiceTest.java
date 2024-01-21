package edu.sru.thangiah.group2.fall2023registrationsystem.service.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Transactions;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.ParentService;

import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParentServiceTest {

    @Mock
    private ParentRepository parentRepository;

    @InjectMocks
    private ParentService parentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindParentByEmailWithExistingEmail() {
        String existingEmail = "daphne@gmail.com";

        Parent result = parentService.findParentByEmail(existingEmail);

        assertNotNull(result);
    }

    @Test
    public void testFindParentByEmailWithNonExistingEmail() {
        String nonExistingEmail = "daphne1111@gmail.com";

        Parent result = parentService.findParentByEmail(nonExistingEmail);

        assertNull(result);
    }

    @Test
    public void testFindParentByEmailWithInvalidInput() {
        String invalidEmail = null;
            parentService.findParentByEmail(invalidEmail);
            fail("IllegalArgumentException expected");
         
    }

    @Test
    public void testGetParentTransactionsWithExistingParent() {
        Integer existingParentId = 2001;
        List<Transactions> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transactions());
        List<Transactions> result = parentService.getParentTransactions(existingParentId);

        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetParentTransactionsWithNoTransactions() {
        Integer existingParentId = 2002;

        List<Transactions> result = parentService.getParentTransactions(existingParentId);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetParentTransactionsWithInvalidParentId() {
        Integer invalidParentId = null;

            parentService.getParentTransactions(invalidParentId);
            fail("IllegalArgumentException expected");
        
    }
}
