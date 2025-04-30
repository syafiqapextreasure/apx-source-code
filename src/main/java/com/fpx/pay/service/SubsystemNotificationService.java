package com.fpx.pay.service;

import com.fpx.pay.model.PaymentNotification;
import com.fpx.pay.model.PaymentInquiryRequest;
import com.fpx.pay.model.PaymentInquiryResponse;

public interface SubsystemNotificationService {
    void sendDirectNotification(PaymentNotification paynetNotification);
    void sendIndirectNotification(PaymentNotification paynetNotification);
    PaymentInquiryResponse handlePaymentInquiry(PaymentInquiryRequest request);
} 