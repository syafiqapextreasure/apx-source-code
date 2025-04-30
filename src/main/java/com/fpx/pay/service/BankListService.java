package com.fpx.pay.service;

import com.fpx.pay.model.BankListResponse;
import java.util.List;

public interface BankListService {
    BankListResponse retrieveIndividualBankList();
    BankListResponse retrieveCorporateBankList();
    String calculateChecksum(String... params);
} 