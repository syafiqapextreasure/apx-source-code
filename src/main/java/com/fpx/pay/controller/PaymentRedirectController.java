package com.fpx.pay.controller;

import com.fpx.pay.service.GetBillNoService;
import com.fpx.pay.service.PaymentRedirectService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class PaymentRedirectController {

    private final PaymentRedirectService paymentRedirectService;
   


    public PaymentRedirectController(PaymentRedirectService paymentRedirectService) {
        this.paymentRedirectService = paymentRedirectService;
    }

    @GetMapping("/redirect-to-payment")
    public String redirectToPayment(@RequestParam String token, Model model) {
           if (!paymentRedirectService.validateToken(token)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or expired token");
        }

        String paymentUrl = paymentRedirectService.getPaymentRedirectUrl(token);
        System.out.println(token+"<====paymentUrl is ==>"+paymentUrl);
        model.addAttribute("paymentUrl", paymentUrl);
        model.addAttribute("token", token);
        return "payment-redirect";
    }
} 