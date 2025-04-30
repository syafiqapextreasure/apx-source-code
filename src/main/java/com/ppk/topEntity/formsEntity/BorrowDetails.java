package com.ppk.topEntity.formsEntity;

import lombok.Data;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Data
@Entity
public class BorrowDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patronId;
    private Date borrowDate;
    private Date returnDate;
    private String paymentMethod;
    private Double paymentAmount;
    private String priceDetails;

    @ManyToMany
    private List<LostBorrowedMaterial> books;  // List of selected books

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatronId() {
		return patronId;
	}

	public void setPatronId(String patronId) {
		this.patronId = patronId;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPriceDetails() {
		return priceDetails;
	}

	public void setPriceDetails(String priceDetails) {
		this.priceDetails = priceDetails;
	}

	public List<LostBorrowedMaterial> getBooks() {
		return books;
	}

	public void setBooks(List<LostBorrowedMaterial> books) {
		this.books = books;
	}

    // Getters and setters
    
    
}

