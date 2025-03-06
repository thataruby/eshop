package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    @Setter
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = validatePayment();
    }

    private String validatePayment() {
        if (method.equals("CASH_ON_DELIVERY")) {
            if (paymentData.get("address") == null || paymentData.get("address").isEmpty()
                    || paymentData.get("deliveryFee") == null || paymentData.get("deliveryFee").isEmpty()) {
                return "REJECTED";
            }
        }
        return "PENDING";
    }
}
