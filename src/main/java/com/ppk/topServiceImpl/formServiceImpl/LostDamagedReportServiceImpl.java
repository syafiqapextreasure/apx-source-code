package com.ppk.topServiceImpl.formServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppk.topEntity.errorEntity.ReportCreationException;
import com.ppk.topEntity.formsEntity.BorrowDetails;
import com.ppk.topEntity.formsEntity.LostBorrowedMaterial;
import com.ppk.topEntity.formsEntity.LostDamagedReport;
import com.ppk.topEntity.formsEntity.LostMaterialForm;
import com.ppk.topRepositry.formRepositry.BorrowDetailsRepository;
import com.ppk.topRepositry.formRepositry.BorrowedItemRepoLost;
import com.ppk.topRepositry.formRepositry.LostBorrowedMaterialRepository;
import com.ppk.topRepositry.formRepositry.LostDamagedReportRepository;
import com.ppk.topRepositry.formRepositry.LostMaterialFormRepository;
import com.ppk.topService.formService.lostForm.LostDamagedReportService;

@Service
public class LostDamagedReportServiceImpl implements LostDamagedReportService {

	private static final Logger logger = LoggerFactory.getLogger(LostDamagedReportServiceImpl.class);

	@Autowired
	private BorrowedItemRepoLost borrowedItemRepoLost;

	@Autowired
	private LostBorrowedMaterialRepository materialRepository;

	@Autowired
	private BorrowDetailsRepository borrowDetailsRepository;

	@Autowired
	private LostMaterialFormRepository lostFormRepository;

	@Autowired
	private LostMaterialFormRepository lostMaterialFormRepository;

	private static final double LATE_FEE_PER_DAY = 0.20;
	private static final double MAX_LATE_FEE = 27.00;
	private static final double PROCESSING_FEE = 10.00;

	public String processPayment(double amount) {
		logger.info("Processing payment for amount: RM {}", amount);

		if (amount <= 0) {
			logger.error("Invalid payment amount: RM {}", amount);
			throw new IllegalArgumentException("Invalid payment amount");
		}

		// Simulate payment processing logic
		String receiptNumber = "RECEIPT" + System.currentTimeMillis();
		logger.info("Payment processed successfully. Receipt number: {}", receiptNumber);
		return receiptNumber;
	}

	// display borrowed material

	@Override
	public List<LostBorrowedMaterial> getBorrowedMaterials() {
		logger.info("Fetching list of borrowed materials");
		// This will change based on user
		List<LostBorrowedMaterial> borrowedMaterials = this.borrowedItemRepoLost.findAll();
		System.err.println(borrowedMaterials);
		
		// If no borrowed materials found, create sample data
		if (borrowedMaterials == null || borrowedMaterials.isEmpty()) {
			logger.info("No borrowed materials found. Creating sample data for display purposes");
			borrowedMaterials = createSampleBorrowedMaterials();
		}
		
		logger.info("Returning {} borrowed materials", borrowedMaterials.size());
		return borrowedMaterials;
	}
	
	/**
	 * Creates sample borrowed materials data for display and testing
	 * when no real data is available
	 */
	private List<LostBorrowedMaterial> createSampleBorrowedMaterials() {
		List<LostBorrowedMaterial> sampleMaterials = new ArrayList<>();
		
		// Sample 1
		LostBorrowedMaterial material1 = new LostBorrowedMaterial();
		material1.setId(1L);
		material1.setAcquisitionNumber("AC123456");
		material1.setBookTitle("Spring Boot in Action");
		material1.setPatronId("P123");
		material1.setBookPrice(45.99);
		sampleMaterials.add(material1);
		
		// Sample 2
		LostBorrowedMaterial material2 = new LostBorrowedMaterial();
		material2.setId(2L);
		material2.setAcquisitionNumber("AC234567");
		material2.setBookTitle("Java Concurrency in Practice");
		material2.setPatronId("P123");
		material2.setBookPrice(39.99);
		sampleMaterials.add(material2);
		
		// Sample 3
		LostBorrowedMaterial material3 = new LostBorrowedMaterial();
		material3.setId(3L);
		material3.setAcquisitionNumber("AC345678");
		material3.setBookTitle("Effective Java");
		material3.setPatronId("P123");
		material3.setBookPrice(49.99);
		sampleMaterials.add(material3);
		
		return sampleMaterials;
	}

	// Save form data
	public LostMaterialForm saveFormData(LostMaterialForm form) {
		return this.lostFormRepository.save(form);
	}

	 public double calculateLateFee(java.util.Date date, java.util.Date date2) {
	        if (date == null || date2 == null) {
	            return 0.0;
	        }

	        long diffInMillis = date2.getTime() - date.getTime();
	        if (diffInMillis <= 0) {
	            return 0.0;
	        }

	        long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);
	        double lateFee = diffInDays * LATE_FEE_PER_DAY;
	        return Math.min(lateFee, MAX_LATE_FEE);
	    }


	 public double calculateTotalPrice(BorrowDetails borrowDetails) {
	        double totalPrice = 0.0;
	        double lateFee = calculateLateFee(borrowDetails.getBorrowDate(), borrowDetails.getReturnDate());

	        for (LostBorrowedMaterial book : borrowDetails.getBooks()) {
	            totalPrice += book.getBookPrice();
	        }
	        totalPrice += lateFee + PROCESSING_FEE;

	        return totalPrice;
	    }

	    public BorrowDetails saveBorrowDetails(BorrowDetails borrowDetails) {
	        double totalPrice = calculateTotalPrice(borrowDetails);
	        borrowDetails.setPaymentAmount(totalPrice);
	        borrowDetails.setPriceDetails("Book Price + Late Fee + Processing Fee");
	        return borrowDetailsRepository.save(borrowDetails);
	    }

	    public List<LostBorrowedMaterial> getAvailableMaterials() {
	        return materialRepository.findAll(); // You can add custom filters here
	    }




	@Override
	public double calculateTotalPrice(LostDamagedReport lostDamagedReport) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Object[]> getAllUniqueLostForms() {
		return lostMaterialFormRepository.getAllLostFormRequestUnique();
	}
	
	 // New method to get data by patron_id
    public List<Object[]> getLostFormRequestByPatronId(String patronId) {
        return lostMaterialFormRepository.getLostFormRequestByPatronId(patronId);
    }

    /**
     * Generates a detailed payment breakdown string from individual components
     * 
     * @param bookPrice The price of the book(s)
     * @param lateFee The late fee amount
     * @param processingFee The processing fee amount
     * @return A formatted string showing the breakdown (e.g., "RM20+RM10+RM1=RM31")
     */
    public String generatePaymentBreakdown(double bookPrice, double lateFee, double processingFee) {
        double total = bookPrice + lateFee + processingFee;
        return String.format("RM%.2f+RM%.2f+RM%.2f=RM%.2f", 
                             bookPrice, lateFee, processingFee, total);
    }

}
