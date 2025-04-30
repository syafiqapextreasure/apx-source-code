package com.fpx.pay.controller;

import com.fpx.pay.model.PaymentToken;
import com.fpx.pay.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/generate")
    public ResponseEntity<PaymentToken> generateToken(
            @RequestHeader("X-SubSystem-ID") String subSystemId,
            @RequestParam Double amount,
            @RequestParam String currency) {
        PaymentToken token = tokenService.generateToken(subSystemId, amount, currency);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<PaymentToken> validateToken(@PathVariable String token) {
        PaymentToken validToken = tokenService.validateToken(token);
        if (validToken != null) {
            return ResponseEntity.ok(validToken);
        }
        return ResponseEntity.notFound().build();
    }
} 