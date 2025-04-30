package com.fpx.pay.service.impl;

import com.fpx.pay.service.PaymentRedirectService;
import org.springframework.stereotype.Service;

@Service
public class PaymentRedirectServiceImpl implements PaymentRedirectService {

    private static final String FPX_UAT_URL = "https://fpxuat.ppj.gov.my";

    @Override
    public String getPaymentRedirectUrl(String token) {
        return FPX_UAT_URL + "/pay/pay";
    }

    @Override
    public boolean validateToken(String token) {
        // TODO: Implement token validation logic
        // This should check if the token is valid and not expired
        return token != null && !token.isEmpty();
    }
} 