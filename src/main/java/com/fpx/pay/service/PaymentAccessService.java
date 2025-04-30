package com.fpx.pay.service;

import com.fpx.pay.model.PaymentAccessPayload;
import com.fpx.pay.model.PaymentAccessResponse;

public interface PaymentAccessService {
    PaymentAccessResponse requestAccess(PaymentAccessPayload payload);
} 