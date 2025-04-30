package com.fpx.pay.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankListRequest {
    private String fpx_msgType;    // BE
    private String fpx_msgToken;   // 01 for Individual, 02 for Corporate
    private String fpx_sellerId;   // Provided by PayNet
    private String fpx_version;    // Message version
    private String fpx_checkSum;   // Calculated checksum
} 