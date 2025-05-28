package com.ppk.topController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppk.topEntity.formsEntity.LostMaterialForm;
import com.ppk.topServiceImpl.formServiceImpl.LostDamagedReportServiceImpl;

@Controller
@RequestMapping("/direct-lost")
public class LostDirectFormController {
    
    private static final Logger logger = LoggerFactory.getLogger(LostDirectFormController.class);

    @Autowired
    private LostDamagedReportServiceImpl lostDamagedReportService;
    
    @GetMapping("/form")
    public String showDirectForm(Model model) {
        try {
            logger.info("Fetching borrowed materials for direct lost form");
            model.addAttribute("borrowedMaterials", lostDamagedReportService.getBorrowedMaterials());
            return "forms/lost-material-direct";
        } catch (Exception e) {
            logger.error("Error loading direct lost form: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "Error loading materials. Please try again.");
            return "error";
        }
    }
    
    @PostMapping("/submit")
    public String submitDirectForm(
            @RequestParam String patronId,
            @RequestParam(required = false) String[] acquisitionNumbers,
            @RequestParam(required = false) String borrowDate,
            @RequestParam(required = false) String returnDate,
            @RequestParam String paymentMethod,
            @RequestParam double totalPayment,
            @RequestParam double bookPriceInput,
            RedirectAttributes redirectAttributes) {
        
        try {
            logger.info("Processing lost material direct form submission for patron: {}", patronId);
            
            // Check if acquisitionNumbers exists and is not empty
            if (acquisitionNumbers == null || acquisitionNumbers.length == 0) {
                redirectAttributes.addFlashAttribute("message", "Error: Please select at least one book");
                return "redirect:/direct-lost/form";
            }
            
            // Process each selected material
            for (String acquisitionNumber : acquisitionNumbers) {
                LostMaterialForm form = new LostMaterialForm();
                form.setPatronId(patronId);
                form.setAcquisitionNumber(acquisitionNumber);
                form.setBorrowDate(borrowDate);
                form.setReturnDate(returnDate);
                form.setPaymentMethod(paymentMethod);
                form.setTotalPayment(totalPayment);
                
                // Calculate payment breakdown using form logic
                double processingFee = 10;
                double lateFee = 0;
                
                // Only calculate late fee if both dates are provided
                if (borrowDate != null && !borrowDate.isEmpty() && returnDate != null && !returnDate.isEmpty()) {
                    java.time.LocalDate d1 = java.time.LocalDate.parse(borrowDate);
                    java.time.LocalDate d2 = java.time.LocalDate.parse(returnDate);
                    long diffDays = java.time.temporal.ChronoUnit.DAYS.between(d1, d2);
                    if (diffDays > 2) {
                        lateFee = Math.min((diffDays - 2) * 0.2, 27);
                    }
                }
                
                double total = bookPriceInput + processingFee + lateFee;
                String breakdown = String.format("Buku: RM%.2f + Pemprosesan: RM%.2f + Denda Lewat: RM%.2f = RM%.2f", bookPriceInput, processingFee, lateFee, total);
                form.setPaymentBreakdown(breakdown);
                
                // Save the form data
                lostDamagedReportService.saveFormData(form);
                logger.info("Saved lost material form for acquisition number: {}", acquisitionNumber);
            }
            
            redirectAttributes.addFlashAttribute("message", "Form submitted successfully!");
            
        } catch (Exception e) {
            logger.error("Error submitting lost material direct form: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("message", "Error submitting form: " + e.getMessage());
        }
        
        return "redirect:/direct-lost/form";
    }
} 