package com.fpx.pay.model;

import lombok.Data;

@Data
public class BankListResponse {
    private String fpx_msgType;    // BC
    private String fpx_msgToken;   // 01 for Individual, 02 for Corporate
    private String fpx_sellerId;   // Provided by PayNet
    private String fpx_bankList;   // List of banks and their status
    private String fpx_checkSum;   // Calculated checksum
} 