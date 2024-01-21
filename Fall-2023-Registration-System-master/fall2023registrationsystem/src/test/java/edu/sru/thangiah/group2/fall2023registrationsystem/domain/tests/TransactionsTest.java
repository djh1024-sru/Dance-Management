package edu.sru.thangiah.group2.fall2023registrationsystem.domain.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Transactions;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionsTest {

    private Transactions transaction;

    @BeforeEach
    public void setUp() {
       
        transaction = new Transactions(1, "CHECK", 50.0f, " ", LocalDateTime.now(), null);
    }

    @Test
    public void testTransactionIDGetterAndSetter() {
        assertEquals(1, transaction.getTransactionID());
        transaction.setTransactionID(2);
        assertEquals(2, transaction.getTransactionID());
    }

    @Test
    public void testAmountGetterAndSetter() {
        assertEquals(50.0f, transaction.getAmount());
        transaction.setAmount(200.0f);
        assertEquals(200.0f, transaction.getAmount());
    }

    @Test
    public void testPaymentTypeGetterAndSetter() {
        assertEquals("CHECK", transaction.getPaymentType());
        transaction.setPaymentType("CASH");
        assertEquals("CASH", transaction.getPaymentType());
    }

    @Test
    public void testCheckNumberGetterAndSetter() {
        assertEquals(" ", transaction.getCheckNumber());
        transaction.setCheckNumber("12345");
        assertEquals("12345", transaction.getCheckNumber());
    }

    @Test
    public void testTransactionTimeGetterAndSetter() {
        LocalDateTime testTime = LocalDateTime.of(2023, 9, 2, 12, 0);
        transaction.setTransactionTime(testTime);
        assertEquals(testTime, transaction.getTransactionTime());
    }

}
