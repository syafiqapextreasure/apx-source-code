package com.ppk.topEntity.roomEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class PersonalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id")
    private Long bookingId;  // Reference to the booking

    @Override
	public String toString() {
		return "PersonalInformation [id=" + id + ", title=" + title + ", name=" + name + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", notes=" + notes+" payment=" + paymentMethod+" ]";
	}

	//    @NotBlank(message = "Title is required")
    private String title;

//    @NotBlank(message = "Name is required")
    private String name;

//    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

//    @Email(message = "Email must be valid")
//    @NotBlank(message = "Email is required")
    private String email;

    @Column(nullable = true)
    private String notes;

//    @NotBlank(message = "Company name is required")
    @Column(nullable = true)
    private String companyName;

//    @NotBlank(message = "Address is required")
    @Column(nullable = true)
    private String address;
 
//    @NotBlank(message = "City is required")
    @Column(nullable = true)
    private String city;

//    @NotBlank(message = "State is required")
    @Column(nullable = true)
    private String state;

//    @NotBlank(message = "Postal code is required")
    @Column(nullable = true)
    private String postalCode;

//    @NotBlank(message = "Country is required")
    @Column(nullable = true)
    private String country;
    // Getters and Setters
    
    @Column(nullable = true)
    private String paymentMethod;

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
    
    public Long getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}

