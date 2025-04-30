package com.fpx.pay.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class SubsystemNotification {
    private String orderNo;
    private String status;        // SUCCESS, PENDING_APPROVAL, FAILED
    private String txnReference;
    private String txnId;
    private LocalDateTime txnTime;
    private BigDecimal amount;
} 