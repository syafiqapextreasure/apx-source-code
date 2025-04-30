package com.fpx.pay.controller;

import com.fpx.pay.model.Payment;
import com.fpx.pay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<Payment> initiatePayment(@RequestParam String token) {
    	System.out.println("token found "+token);
        Payment payment = paymentService.initiatePayment(token);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/banks")
    public ResponseEntity<List<String>> getAvailableBanks() {
        List<String> banks = paymentService.getAvailableBanks();
        return ResponseEntity.ok(banks);
    }

    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(
            @RequestParam String paymentReference,
            @RequestParam String bankCode,
            @RequestParam String paymentType) {
        Payment payment = paymentService.processPayment(paymentReference, bankCode, paymentType);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/callback")
    public ResponseEntity<Void> paymentCallback(
            @RequestParam String paymentReference,
            @RequestParam String status) {
        paymentService.updatePaymentStatus(paymentReference, status);
        paymentService.notifySubSystem(paymentReference);
        return ResponseEntity.ok().build();
    }
} 