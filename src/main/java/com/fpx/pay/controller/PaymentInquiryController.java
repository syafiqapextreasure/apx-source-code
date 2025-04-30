package com.fpx.pay.controller;

import com.fpx.pay.model.PaymentInquiryRequest;
import com.fpx.pay.model.PaymentInquiryResponse;
import com.fpx.pay.service.SubsystemNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pay-api/pay")
public class PaymentInquiryController {

    private final SubsystemNotificationService subsystemNotificationService;

    public PaymentInquiryController(SubsystemNotificationService subsystemNotificationService) {
        this.subsystemNotificationService = subsystemNotificationService;
    }

    @PostMapping("/inquiry")
    public ResponseEntity<PaymentInquiryResponse> inquirePaymentStatus(
            @Valid @RequestBody PaymentInquiryRequest request) {
        PaymentInquiryResponse response = subsystemNotificationService.handlePaymentInquiry(request);
        return ResponseEntity.ok(response);
    }
} 