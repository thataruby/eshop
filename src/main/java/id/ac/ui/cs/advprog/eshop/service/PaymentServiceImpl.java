package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String status = validatePayment(method, paymentData);
        Payment payment = new Payment(UUID.randomUUID().toString(), method, status, paymentData);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    private String validatePayment(String method, Map<String, String> paymentData) {
        if (method.equals("VoucherCode")) {
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode != null && voucherCode.length() == 16
                    && voucherCode.startsWith("ESHOP")
                    && voucherCode.replaceAll("[^0-9]", "").length() == 8) {
                return "SUCCESS";
            }
            return "REJECTED";
        }
        else if (method.equals("CashOnDelivery")) {
            if (paymentData.get("address") == null || paymentData.get("address").isEmpty()
                    || paymentData.get("deliveryFee") == null || paymentData.get("deliveryFee").isEmpty()) {
                return "REJECTED";
            }
            return "PENDING";
        }
        return "PENDING";
    }
}
