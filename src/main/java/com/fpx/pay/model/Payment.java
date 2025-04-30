package com.fpx.pay.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paymentReference;

    @OneToOne
    @JoinColumn(name = "token_id", nullable = false)
    private PaymentToken paymentToken;

    @Column(nullable = false)
    private String bankCode;

    @Column(nullable = false)
    private String paymentType;

    @Column(nullable = false)
    private String status;

    private String transactionId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime completedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = "INITIATED";
    }
} 