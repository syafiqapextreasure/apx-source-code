package com.fpx.pay.service;

import com.fpx.pay.model.CorporatePayment;
import com.fpx.pay.model.PaymentNotification;
import com.fpx.pay.model.PaymentRequest;

public interface CorporatePaymentService {
    CorporatePayment createCorporatePayment(PaymentRequest request);
    void handlePaymentApproval(PaymentNotification notification);
    void processExpiredPayments();
} 