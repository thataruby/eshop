package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private Payment payment1, payment2;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        Map<String, String> data1 = new HashMap<>();
        data1.put("voucherCode", "ESHOP1234ABC5678");
        payment1 = new Payment("PAY-001", "VoucherCode", "SUCCESS", data1);

        Map<String, String> data2 = new HashMap<>();
        data2.put("address", "123 Main Street");
        data2.put("deliveryFee", "5000");
        payment2 = new Payment("PAY-002", "CashOnDelivery", "REJECTED", data2);
    }

    @Test
    void testSaveNewPayment() {
        Payment result = paymentRepository.save(payment1);
        assertEquals("PAY-001", result.getId());
        assertEquals("SUCCESS", result.getStatus());
        assertEquals("VoucherCode", result.getMethod());

        List<Payment> payments = paymentRepository.findAll();
        assertEquals(1, payments.size());
    }

    @Test
    void testSaveUpdateExistingPayment() {
        paymentRepository.save(payment1);
        Payment updatedPayment = new Payment("PAY-001", "VoucherCode", "FAILED", payment1.getPaymentData());

        Payment result = paymentRepository.save(updatedPayment);

        assertEquals("PAY-001", result.getId());
        assertEquals("FAILED", result.getStatus());

        List<Payment> payments = paymentRepository.findAll();
        assertEquals(1, payments.size()); // Ensure it doesn't duplicate
    }

    @Test
    void testFindByIdIfExists() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        Payment result = paymentRepository.findById("PAY-002");
        assertNotNull(result);
        assertEquals("CashOnDelivery", result.getMethod());
        assertEquals("REJECTED", result.getStatus());
    }

    @Test
    void testFindByIdIfNotExists() {
        paymentRepository.save(payment1);
        Payment result = paymentRepository.findById("NON_EXISTENT");
        assertNull(result);
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> payments = paymentRepository.findAll();
        assertEquals(2, payments.size());
    }
}
