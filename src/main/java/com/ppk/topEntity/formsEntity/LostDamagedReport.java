package com.ppk.topEntity.formsEntity;

import java.time.LocalDate;



import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

//BORANG ADUAN BAHAN HILANG /LOST MATERIAL COMPLAINT FORM

import javax.persistence.*;
import java.util.List;

//BORANG ADUAN BAHAN HILANG / LOST MATERIAL COMPLAINT FORM
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "lost_damaged_reports")
public class LostDamagedReport {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	   private String patronId;
	    private String acquisitionNumber;
	    private String bookTitle;
	    private String borrowDate;
	    private String returnDate;
	    private String paymentMethod;
	    
	    @Transient
	    private double bookPrice;
	    @Transient
	    private double lateFee;

    

   
}
