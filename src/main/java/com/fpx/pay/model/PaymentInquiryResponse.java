package com.fpx.pay.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class PaymentInquiryResponse {
    private String subsysId;
    private String orderNo;
    private BigDecimal amount;
    private String status;
    private String failedReason;
} 