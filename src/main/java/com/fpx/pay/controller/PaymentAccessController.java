package com.fpx.pay.controller;

import com.fpx.pay.model.PaymentAccessPayload;
import com.fpx.pay.model.PaymentAccessResponse;
import com.fpx.pay.service.PaymentAccessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pay-api/pay")
public class PaymentAccessController {

    private final PaymentAccessService paymentAccessService;

    public PaymentAccessController(PaymentAccessService paymentAccessService) {
        this.paymentAccessService = paymentAccessService;
    }

    @PostMapping("/access")
    public ResponseEntity<PaymentAccessResponse> requestAccess(
            @Valid @RequestBody PaymentAccessPayload payload) {

                System.out.println("Request in method");
        PaymentAccessResponse response = paymentAccessService.requestAccess(payload);
        return ResponseEntity.ok(response);
    }
} 