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
        paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentWithValidCashOnDelivery() {
        paymentData.put("address", "Jl. Merdeka No.1");
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("1", "CASH_ON_DELIVERY", paymentData);

        assertEquals("1", payment.getId());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertEquals("PENDING", payment.getStatus()); // Default status
    }

    @Test
    void testRejectCashOnDeliveryWithEmptyAddress() {
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("2", "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectCashOnDeliveryWithNullFee() {
        paymentData.put("address", "Jl. Merdeka No.1");
        paymentData.put("deliveryFee", null);

        Payment payment = new Payment("3", "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
}
