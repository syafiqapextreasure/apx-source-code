package com.ppk.topController.lost.LostMaterialController;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.ppk.lost.material.model.CirBahanRosakHilangtblPinjaman;
import com.ppk.topController.lost.LostMaterial.service.CirBahanRosakHilangService;
import com.ppk.topEntity.formsEntity.LostMaterialForm;
import com.ppk.topServiceImpl.formServiceImpl.LostDamagedReportServiceImpl;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lostForm")
public class MaterialLostController {

    @Autowired
    private CirBahanRosakHilangService service;
    
    @Autowired
    private LostDamagedReportServiceImpl lostDamagedReportService;
    
    @GetMapping("/GetLoadtblPinjaman")
    public ResponseEntity<List<CirBahanRosakHilangtblPinjaman>> getLoadTblPinjaman(@RequestParam String idlogin) throws SQLException, InterruptedException {
    	System.out.println("idlogin is"+idlogin);  
    	List<CirBahanRosakHilangtblPinjaman> list = service.getBahanRosakHilangByPatronId(idlogin);
       System.out.println("list is final response "+new Gson().toJson(list));
    	return ResponseEntity.ok(list);   
    }

    // Add a direct submission method that doesn't require acquisitionNumbers
    @PostMapping("/submit-direct")
    public String submitDirectForm(
            @RequestParam("patronId") String patronId,
            @RequestParam("acquisitionNumber") String acquisitionNumber,
            @RequestParam("borrowDate") String borrowDate,
            @RequestParam("returnDate") String returnDate,
            @RequestParam("paymentMethod") String paymentMethod,
            @RequestParam("totalPayment") Double totalPayment,
            Model model) {
        
        try {
            // Create a new lost material form
            LostMaterialForm form = new LostMaterialForm();
            form.setPatronId(patronId);
            form.setAcquisitionNumber(acquisitionNumber);
            form.setBorrowDate(borrowDate);
            form.setReturnDate(returnDate);
            form.setPaymentMethod(paymentMethod);
            form.setTotalPayment(totalPayment);
            
            // Create a payment breakdown
            double bookPrice = totalPayment * 0.7;
            double lateFee = totalPayment * 0.2;
            double processingFee = totalPayment * 0.1;
            String breakdown = String.format("Book: RM%.2f + Late Fee: RM%.2f + Processing: RM%.2f = RM%.2f", 
                                         bookPrice, lateFee, processingFee, totalPayment);
            form.setPaymentBreakdown(breakdown);
            
            // Save the form
            lostDamagedReportService.saveFormData(form);
            
            model.addAttribute("success", true);
            model.addAttribute("message", "Lost material form submitted successfully!");
            return "forms/success";
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Error submitting form: " + e.getMessage());
            return "forms/error";
        }
    }

    @GetMapping("/direct-form")
    public String showDirectForm(Model model) {
        return "forms/lost-material-direct";
    }
}