package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private Order order;
    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;
    private Map<String, String> validCODData;
    private Map<String, String> invalidCODData;
    private Payment paymentVoucher;
    private Payment paymentCOD;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setProductId("P001");
        product.setProductName("Sample Product");
        product.setProductQuantity(1);
        productList.add(product);

        order = new Order(UUID.randomUUID().toString(), productList, System.currentTimeMillis(), "customer123");

        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP1234ABC5678");

        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALIDCODE");

        validCODData = new HashMap<>();
        validCODData.put("address", "123 Main Street");
        validCODData.put("deliveryFee", "5000");

        invalidCODData = new HashMap<>();
        invalidCODData.put("address", "");
        invalidCODData.put("deliveryFee", "5000");

        paymentVoucher = new Payment("PAY-001", "VoucherCode", "SUCCESS", validVoucherData);
        paymentCOD = new Payment("PAY-002", "CashOnDelivery", "PENDING", validCODData);
    }

    @Test
    void testAddPaymentVoucherSuccess() {
        doReturn(paymentVoucher).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, "VoucherCode", validVoucherData);

        assertNotNull(result);
        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentVoucherRejected() {
        doReturn(new Payment("PAY-003", "VoucherCode", "REJECTED", invalidVoucherData))
                .when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, "VoucherCode", invalidVoucherData);

        assertEquals("REJECTED", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentCODSuccess() {
        doReturn(paymentCOD).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, "CashOnDelivery", validCODData);

        assertEquals("PENDING", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentCODRejected() {
        doReturn(new Payment("PAY-004", "CashOnDelivery", "REJECTED", invalidCODData))
                .when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, "CashOnDelivery", invalidCODData);

        assertEquals("REJECTED", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusSuccess() {
        doReturn(paymentVoucher).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(paymentVoucher, "SUCCESS");

        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusRejected() {
        doReturn(paymentVoucher).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(paymentVoucher, "REJECTED");

        assertEquals("REJECTED", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testGetPaymentById() {
        doReturn(paymentVoucher).when(paymentRepository).findById("PAY-001");

        Payment result = paymentService.getPayment("PAY-001");

        assertNotNull(result);
        assertEquals("PAY-001", result.getId());
        verify(paymentRepository, times(1)).findById("PAY-001");
    }

    @Test
    void testGetPaymentByIdNotFound() {
        doReturn(null).when(paymentRepository).findById("NON_EXISTENT");

        Payment result = paymentService.getPayment("NON_EXISTENT");

        assertNull(result);
        verify(paymentRepository, times(1)).findById("NON_EXISTENT");
    }

    @Test
    void testGetAllPayments() {
        doReturn(List.of(paymentVoucher, paymentCOD)).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(2, result.size());
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void testGetAllPaymentsEmpty() {
        doReturn(Collections.emptyList()).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(0, result.size());
        verify(paymentRepository, times(1)).findAll();
    }
}
