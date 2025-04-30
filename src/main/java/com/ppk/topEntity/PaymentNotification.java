package com.ppk.topEntity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentNotification {
    private String fpx_msgType;            // AC
    private String fpx_msgToken;           // 01 or 02
    private String fpx_sellerId;
    private String fpx_sellerExOrderNo;
    private String fpx_sellerTxnTime;      // YYYYMMDDHHmmSS
    private String fpx_sellerOrderNo;
    private String fpx_sellerBankCode;
    private String fpx_txnCurrency;        // MYR
    private BigDecimal fpx_txnAmount;
    private Long fpx_txnId;
    private String fpx_txnTime;            // YYYYMMDDHHmmSS
    private String fpx_checkSum;
    private String fpx_debitAuthCode;
    private String fpx_debitAuthNo;
    private String fpx_creditAuthCode;
    private String fpx_creditAuthNo;
    private Integer fpx_xtraInfo;
    private String fpx_buyerEmail;
    private String fpx_buyerName;
    private String fpx_buyerBankBranch;
    private String fpx_buyerIban;
    private String fpx_buyerId;
    private String fpx_makerName;
} 