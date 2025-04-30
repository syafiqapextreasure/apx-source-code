package com.fpx.pay.service.impl;

import com.fpx.pay.model.CorporatePayment;
import com.fpx.pay.model.PaymentNotification;
import com.fpx.pay.model.PaymentRequest;
import com.fpx.pay.repository.CorporatePaymentRepository;
import com.fpx.pay.service.CorporatePaymentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CorporatePaymentServiceImpl implements CorporatePaymentService {

    private final CorporatePaymentRepository corporatePaymentRepository;

    public CorporatePaymentServiceImpl(CorporatePaymentRepository corporatePaymentRepository) {
        this.corporatePaymentRepository = corporatePaymentRepository;
    }

    @Override
    @Transactional
    public CorporatePayment createCorporatePayment(PaymentRequest request) {
        CorporatePayment payment = new CorporatePayment();
        payment.setSellerOrderNo(request.getFpx_sellerOrderNo());
        payment.setSellerExOrderNo(request.getFpx_sellerExOrderNo());
        payment.setAmount(request.getFpx_txnAmount());
        payment.setBuyerBankId(request.getFpx_buyerBankId());
        
        return corporatePaymentRepository.save(payment);
    }

    @Override
    @Transactional
    public void handlePaymentApproval(PaymentNotification notification) {
        corporatePaymentRepository.findBySellerOrderNo(notification.getFpx_sellerOrderNo())
            .ifPresent(payment -> {
                payment.setStatus("APPROVED");
                payment.setApprovedAt(LocalDateTime.now());
                payment.setDebitAuthCode(notification.getFpx_debitAuthCode());
                payment.setDebitAuthNo(notification.getFpx_debitAuthNo());
                payment.setCreditAuthCode(notification.getFpx_creditAuthCode());
                payment.setCreditAuthNo(notification.getFpx_creditAuthNo());
                payment.setFpxTxnId(notification.getFpx_txnId());
                
                corporatePaymentRepository.save(payment);
            });
    }

    @Override
    @Scheduled(cron = "0 0 * * * *") // Run every hour
    @Transactional
    public void processExpiredPayments() {
        LocalDateTime now = LocalDateTime.now();
        corporatePaymentRepository.findExpiredPayments(now)
            .forEach(payment -> {
                payment.setStatus("EXPIRED");
                corporatePaymentRepository.save(payment);
                // TODO: Notify subsystem about expired payment
            });
    }
} 