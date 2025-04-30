package com.fpx.pay.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class PaymentInquiryRequest {
    @NotBlank
    private String subsysId;

    @NotBlank
    private String password;

    @NotBlank
    private String orderNo;
} 