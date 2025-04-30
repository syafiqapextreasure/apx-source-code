package com.fpx.pay.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "corporate_payments")
public class CorporatePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sellerOrderNo;

    @Column(nullable = false)
    private String sellerExOrderNo;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String buyerBankId;

    @Column(nullable = false)
    private String status;  // PENDING, APPROVED, CANCELLED, EXPIRED

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiryDate;  // 7 days from creation

    private LocalDateTime approvedAt;

    private String debitAuthCode;
    private String debitAuthNo;
    private String creditAuthCode;
    private String creditAuthNo;
    private Long fpxTxnId;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        expiryDate = createdAt.plusDays(7);
        status = "PENDING";
    }
} 