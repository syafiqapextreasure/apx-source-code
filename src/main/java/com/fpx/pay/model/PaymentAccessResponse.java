package com.fpx.pay.model;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAccessResponse {
    private String token;
    private String token_type;
    private String expires_in;
} 