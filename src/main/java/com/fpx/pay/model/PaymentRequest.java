package com.fpx.pay.model;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
public class PaymentRequest {
    @NotBlank
    @Size(max = 2)
    private String fpx_msgType;        // AR

    @NotBlank
    @Size(max = 2)
    private String fpx_msgToken;       // 01 or 02

    @NotBlank
    @Size(max = 10)
    private String fpx_sellerId;

    @NotBlank
    @Size(max = 40)
    private String fpx_sellerExOrderNo;

    @NotBlank
    @Pattern(regexp = "\\d{14}")      // YYYYMMDDHHmmSS
    private String fpx_sellerTxnTime;

    @NotBlank
    @Size(max = 40)
    private String fpx_sellerOrderNo;

    @NotBlank
    @Size(max = 2)
    private String fpx_sellerBankCode;

    @NotBlank
    @Size(max = 3)
    private String fpx_txnCurrency;    // MYR

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("999999999999999.99")
    private BigDecimal fpx_txnAmount;

    @NotBlank
    @Size(max = 10)
    private String fpx_buyerBankId;

    @NotBlank
    @Size(max = 30)
    private String fpx_productDesc;

    @NotNull
    private Integer fpx_version;

    @NotBlank
    private String fpx_checkSum;

    @Email
    @Size(max = 50)
    private String fpx_buyerEmail;

    @Size(max = 40)
    private String fpx_buyerName;

    @Size(max = 10)
    private String fpx_buyerBankBranch;

    @Size(max = 20)
    private String fpx_buyerAcctNo;

    @Size(max = 20)
    private String fpx_buyerId;

    @Size(max = 40)
    private String fpx_makerName;

    @Size(max = 40)
    private String fpx_eaccountNum;

    @Size(max = 40)
    private String fpx_ebuyerId;
} 