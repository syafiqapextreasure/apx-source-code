package com.ppk.topServiceImpl.emailServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ppk.topEntity.formsEntity.MaterialProcurementProposalForm;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${notification.staff.email}")
    private String staffEmail;
    
    // Set this to true to actually send emails, false to just log them
    private static final boolean SEND_ACTUAL_EMAILS = true;
    
    /**
     * Sends an email notification to staff about a new material suggestion submission
     * In development mode, it only logs the email content without sending
     * 
     * @param form The material procurement proposal form that was submitted
     * @return true if email was logged/sent successfully, false otherwise
     */
 public boolean sendMaterialSuggestionNotification(MaterialProcurementProposalForm form) {
    try {
        SimpleMailMessage message = new SimpleMailMessage();
        // Ensure staffEmail is not null or empty before adding to list to avoid issues
        List<String> recipients = new ArrayList<>();
        if (staffEmail != null && !staffEmail.trim().isEmpty()) {
            recipients.add(staffEmail);
        }
        recipients.add("nornie@ppj.gov.my");
        recipients.add("kefav13324@jazipo.com");

        message.setTo(recipients.toArray(new String[0]));
        message.setSubject("New Material Suggestion: " + form.getTitle());
        String emailBody = createMaterialSuggestionEmailBody(form);
        message.setText(emailBody);

        // Development mode - just log the email instead of sending it
        if (!SEND_ACTUAL_EMAILS) {
            logger.info("==================== EMAIL NOTIFICATION ====================");
            logger.info("TO: {}", recipients);
            logger.info("SUBJECT: New Material Suggestion: {}", form.getTitle());
            logger.info("BODY: \n{}", emailBody);
            logger.info("===========================================================");
            logger.info("Email notification logged successfully (development mode)");

            System.out.println("==================== EMAIL NOTIFICATION ====================");
            System.out.println("TO: " + recipients);
            System.out.println("SUBJECT: New Material Suggestion: " + form.getTitle());
            System.out.println("BODY: \n" + emailBody);
            System.out.println("===========================================================");
            System.out.println("Email notification logged successfully (development mode)");

            return true;
        }

        // Production mode - actually send the email
        logger.info("Sending email notification to {} about material suggestion: {}", recipients, form.getTitle());
        System.out.println("Sending email notification to " + recipients + " about material suggestion: " + form.getTitle());

        mailSender.send(message);

        logger.info("Email notification sent successfully");
        System.out.println("Email notification sent successfully");

        return true;
    } catch (Exception e) {
        logger.error("Failed to send email notification: {}", e.getMessage(), e);
        System.out.println("Failed to send email notification: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

    
    /**
     * Creates the email body for material suggestion notifications
     */
    private String createMaterialSuggestionEmailBody(MaterialProcurementProposalForm form) {
        StringBuilder body = new StringBuilder();
        body.append("New Material Suggestion Submitted\n\n");
        body.append("Details:\n");
        body.append("ID: ").append(form.getId()).append("\n");
        body.append("Name: ").append(form.getNama()).append("\n");
        body.append("Email: ").append(form.getEmail()).append("\n");
        body.append("Title: ").append(form.getTitle()).append("\n");
        body.append("Subject: ").append(form.getSubjek()).append("\n");
        body.append("ISBN: ").append(form.getIsbn()).append("\n");
        body.append("Author: ").append(form.getAuthor()).append("\n");
        body.append("Publisher: ").append(form.getPenerbit()).append("\n");
        body.append("Edition: ").append(form.getEdisi()).append("\n");
        body.append("Copies: ").append(form.getBilSalinan()).append("\n");
        body.append("Material Type: ").append(form.getJenisBahan()).append("\n");
        body.append("Sets: ").append(form.getBilSet()).append("\n");
        
        body.append("\nThis is an automated notification. Please do not reply to this email.");
        
        return body.toString();
    }
} 