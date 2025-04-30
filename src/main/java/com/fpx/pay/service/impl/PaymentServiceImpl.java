package com.fpx.pay.service.impl;

import com.fpx.pay.model.Payment;
import com.fpx.pay.model.PaymentToken;
import com.fpx.pay.service.PaymentService;
import com.fpx.pay.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TokenService tokenService;

    @Override
    public Payment initiatePayment(String token) {
        PaymentToken validToken = tokenService.validateToken(token);
        if (validToken == null) {
            throw new RuntimeException("Invalid token");
        }

        Payment payment = new Payment();
        payment.setPaymentToken(validToken);
        payment.setPaymentReference(UUID.randomUUID().toString());
        return payment;
    }

    @Override
    public Payment processPayment(String paymentReference, String bankCode, String paymentType) {
        // TODO: Implement payment processing logic with PayNet
        return null;
    }

    @Override
    public void updatePaymentStatus(String paymentReference, String status) {
        // TODO: Implement payment status update logic
    }

    @Override
    public void notifySubSystem(String paymentReference) {
        // TODO: Implement subsystem notification logic
    }

    @Override
    public List<String> getAvailableBanks() {
        // TODO: Implement actual bank list retrieval from PayNet
        return Arrays.asList("MAYBANK", "CIMB", "PUBLIC_BANK", "RHB");
    }
} 