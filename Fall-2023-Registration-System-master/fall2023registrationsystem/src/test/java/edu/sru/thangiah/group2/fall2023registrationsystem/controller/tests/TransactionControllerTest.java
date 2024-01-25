package edu.sru.thangiah.group2.fall2023registrationsystem.controller.tests;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import java.time.LocalDateTime;

import edu.sru.thangiah.group2.fall2023registrationsystem.controller.TransactionController;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;


public class TransactionControllerTest {

    private TransactionController transactionController;
    private Model mockModel;

    @BeforeEach
    public void setUp() {

        transactionController = new TransactionController();
        mockModel = mock(Model.class);
    }

    @Test
    public void testShowPaymentForm() {
        Integer parentID = 2003;

        String viewName = transactionController.showPaymentForm(mockModel, parentID);
        assertEquals("payment", viewName);
    }

    @Test
    public void testProcessPayment() {
        Integer transactionID = null; 
        String paymentType = "CASH";
        Float amount = 100.0f;
        String checkNumber = null; 
        LocalDateTime transactionTime = LocalDateTime.now();
        Parent parentID = new Parent();

        String result = transactionController.processPayment(transactionID, paymentType, amount, checkNumber, transactionTime, parentID);

        assertNotNull(result);

    }

    public void testParentPaymentForm() {

        String viewName = transactionController.parentPaymentForm(mockModel);

        assertEquals("parentPayment", viewName);
    }

}
