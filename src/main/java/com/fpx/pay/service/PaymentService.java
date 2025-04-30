package com.fpx.pay.service;

import com.fpx.pay.model.Payment;
import java.util.List;

public interface PaymentService {
    Payment initiatePayment(String token);
    Payment processPayment(String paymentReference, String bankCode, String paymentType);
    void updatePaymentStatus(String paymentReference, String status);
    void notifySubSystem(String paymentReference);
    List<String> getAvailableBanks();
} 