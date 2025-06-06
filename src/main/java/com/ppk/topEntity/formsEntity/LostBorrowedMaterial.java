package com.ppk.topEntity.formsEntity;

import javax.annotation.Generated;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "list_borroed_materials")
@Data
public class LostBorrowedMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String acquisitionNumber;
    private String bookTitle;
    private Double bookPrice;
    private String patronId;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAcquisitionNumber() {
		return acquisitionNumber;
	}
	public void setAcquisitionNumber(String acquisitionNumber) {
		this.acquisitionNumber = acquisitionNumber;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public Double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getPatronId() {
		return patronId;
	}
	public void setPatronId(String patronId) {
		this.patronId = patronId;
	}
    
    

    // Getters and setters
}


