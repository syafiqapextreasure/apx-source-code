package com.fpx.pay.service;

import com.fpx.pay.model.PaymentToken;

public interface TokenService {
    PaymentToken generateToken(String subSystemId, Double amount, String currency);
    PaymentToken validateToken(String token);
    void invalidateToken(String token);
} 