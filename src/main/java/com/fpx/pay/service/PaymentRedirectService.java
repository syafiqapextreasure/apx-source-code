package com.fpx.pay.service;

public interface PaymentRedirectService {
    String getPaymentRedirectUrl(String token);
    boolean validateToken(String token);
} 