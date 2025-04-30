package com.ppk.topEntity.formsEntity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lost_material_form")
public class LostMaterialForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patronId;          // Patron ID
    private String acquisitionNumber; // No Perolehan
    private String borrowDate;         // Tarikh Pinjaman
    private String returnDate;         // Tarikh Pulangan
    private String paymentMethod;    // Kaedah Pembayaran
    private Double totalPayment;     // Jumlah Pembayaran (RM)

    // Getters and setters
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

    public String getAcquisitionNumber() {
        return acquisitionNumber;
    }

    public void setAcquisitionNumber(String acquisitionNumber) {
        this.acquisitionNumber = acquisitionNumber;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }
}