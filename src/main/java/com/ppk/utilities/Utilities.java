package com.ppk.utilities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.ppk.topEntity.PaymentAccessPayload;
import com.ppk.topEntity.PaymentAccessRequest;
 


public class Utilities {
	  private static final Logger logger = LoggerFactory.getLogger(Utilities.class);

	    private final JavaMailSender emailSender;

	    public Utilities(JavaMailSender emailSender) {
	        this.emailSender = emailSender;
	    }
	
	    
	    //Email sending method
	public String sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
            logger.info("Email sent successfully to {}", to);
            return "Email sent successfully";
        } catch (MailException ex) {
            logger.error("Failed to send email to {}. Error: {}", to, ex.getMessage(), ex);
            return "Failed to send email. Please try again later.";
        }
    }
	
	public PaymentAccessPayload paymentProcessModel(String amount,String petronId) {
		PaymentAccessPayload accessPayload=new PaymentAccessPayload();
		PaymentAccessRequest accessRequest=new PaymentAccessRequest();
		accessRequest.setSubsysId("PPK");
		accessRequest.setPassword("pPk@2022WLm");
		UUID uuid = UUID.randomUUID();
		String orderNumber="PPK0000000"+Math.abs(uuid.getLeastSignificantBits());;
		accessRequest.setOrderNo(orderNumber);
		accessRequest.setDescription(petronId);
		  LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	        String formattedDateTime = now.format(formatter);
		accessRequest.setTxnTime(formattedDateTime);
		accessRequest.setFeeCode("");
		 try {
	            BigDecimal decimalValue = new BigDecimal(amount);
	            accessRequest.setAmount(decimalValue);
	            System.out.println("Decimal value: " + decimalValue);
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid number format!");
	        }
		 accessPayload.setPayload(accessRequest);
		return accessPayload;
//		{"payload": {
//			"subsysId": "PPK",
//			"password": "pPk@2022WLm",
//			"orderNo": "PPK0000000246",
//			"description": "PPK0000000246",
//			"txnTime": "2025-04-23 08:16:00",
//			"feeCode": "",
//			"amount": "3.00"
//						}
//	            }
	}
}
