package com.fpx.pay.service.impl;

import com.fpx.pay.model.PaymentToken;
import com.fpx.pay.service.TokenService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public PaymentToken generateToken(String subSystemId, Double amount, String currency) {
        PaymentToken token = new PaymentToken();
        token.setToken(UUID.randomUUID().toString());
        token.setSubSystemId(subSystemId);
        token.setAmount(amount);
        token.setCurrency(currency);
        token.setExpiryTime(LocalDateTime.now().plusHours(1));
        return token;
    }

    @Override
    public PaymentToken validateToken(String token) {
        // TODO: Implement token validation logic
        return null;
    }

    @Override
    public void invalidateToken(String token) {
        // TODO: Implement token invalidation logic
    }
} 