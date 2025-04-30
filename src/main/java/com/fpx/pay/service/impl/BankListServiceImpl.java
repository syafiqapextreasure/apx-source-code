package com.fpx.pay.service.impl;

import com.fpx.pay.model.BankListRequest;
import com.fpx.pay.model.BankListResponse;
import com.fpx.pay.service.BankListService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class BankListServiceImpl implements BankListService {

    private static final String FPX_MSG_TYPE = "BE";
    private static final String FPX_VERSION = "6.0"; // Update this according to PayNet's requirements
    private static final String INDIVIDUAL_TOKEN = "01";
    private static final String CORPORATE_TOKEN = "02";

    //@Value("${payment.paynet.seller-id}")
    private String sellerId;

    //@Value("${payment.paynet.api-url}")
    private String paynetApiUrl;

    private final RestTemplate restTemplate;

    public BankListServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BankListResponse retrieveIndividualBankList() {
        return retrieveBankList(INDIVIDUAL_TOKEN);
    }

    @Override
    public BankListResponse retrieveCorporateBankList() {
        return retrieveBankList(CORPORATE_TOKEN);
    }

    private BankListResponse retrieveBankList(String msgToken) {
        BankListRequest request = createBankListRequest(msgToken);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        HttpEntity<BankListRequest> httpEntity = new HttpEntity<>(request, headers);
        
        return restTemplate.postForObject(
            paynetApiUrl + "/FPXMain/RetrieveBankList",
            httpEntity,
            BankListResponse.class
        );
    }

    private BankListRequest createBankListRequest(String msgToken) {
        String checksum = calculateChecksum(FPX_MSG_TYPE, msgToken, sellerId, FPX_VERSION);
        
        return BankListRequest.builder()
            .fpx_msgType(FPX_MSG_TYPE)
            .fpx_msgToken(msgToken)
            .fpx_sellerId(sellerId)
            .fpx_version(FPX_VERSION)
            .fpx_checkSum(checksum)
            .build();
    }

    @Override
    public String calculateChecksum(String... params) {
        try {
            // Concatenate all parameters
            String data = String.join("", params);
            
            // Create SHA-1 hash
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(data.getBytes());
            
            // Convert to Base64
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to calculate checksum", e);
        }
    }
} 