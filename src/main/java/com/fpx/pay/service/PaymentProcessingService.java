package com.fpx.pay.service;

import com.fpx.pay.model.PaymentRequest;
import com.fpx.pay.model.PaymentNotification;

public interface PaymentProcessingService {
    void processPayment(PaymentRequest request);
    void handleDirectNotification(PaymentNotification notification);
    void handleIndirectNotification(PaymentNotification notification);
    String calculateChecksum(String... params);
} 