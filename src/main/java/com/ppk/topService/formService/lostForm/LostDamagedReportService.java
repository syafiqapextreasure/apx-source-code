package com.ppk.topService.formService.lostForm;
import java.util.*;
import java.time.LocalDate;


import com.ppk.topEntity.errorEntity.ReportCreationException;
import com.ppk.topEntity.formsEntity.LostBorrowedMaterial;
import com.ppk.topEntity.formsEntity.LostDamagedReport;

public interface LostDamagedReportService {
	
//	public LostDamagedReport createReport(LostDamagedReport damagedReport) throws ReportCreationException;
  public double calculateTotalPrice(LostDamagedReport lostDamagedReport);
  public String processPayment(double ammount);
  public List<LostBorrowedMaterial> getBorrowedMaterials();
  public List<Object[]> getAllUniqueLostForms();
  
}
