package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        // Setting up sample payment data
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testPaymentConstructorWithDefaultStatus() {
        Payment payment = new Payment("PAY-001", "VoucherCode", paymentData);

        assertEquals("PAY-001", payment.getId());
        assertEquals("VoucherCode", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testPaymentConstructorWithCustomStatus() {
        Payment payment = new Payment("PAY-002", "VoucherCode", "SUCCESS", paymentData);

        assertEquals("PAY-002", payment.getId());
        assertEquals("VoucherCode", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testPaymentDataIntegrity() {
        Payment payment = new Payment("PAY-003", "VoucherCode", "SUCCESS", paymentData);

        assertTrue(payment.getPaymentData().containsKey("voucherCode"));
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testEmptyPaymentData() {
        Payment emptyPayment = new Payment("PAY-005", "BankTransfer", new HashMap<>());

        assertEquals("PAY-005", emptyPayment.getId());
        assertEquals("BankTransfer", emptyPayment.getMethod());
        assertEquals("PENDING", emptyPayment.getStatus());
        assertTrue(emptyPayment.getPaymentData().isEmpty());
    }
}
