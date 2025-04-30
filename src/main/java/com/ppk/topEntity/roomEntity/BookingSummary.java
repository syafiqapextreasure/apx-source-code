package com.ppk.topEntity.roomEntity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class BookingSummary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double roomCost; // Room cost (calculated)
	private double equipmentCost; // Equipment cost (calculated)
	private double subtotal; // Subtotal (room cost + equipment cost)
	private double tax; // Tax (percentage of subtotal)
	private double total; // Total (subtotal + tax)
	private double deposit; // Deposit (can be configurable)

//	@NotNull(message = "Room booking details are required")
//	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private RoomBookingDetails roomDetails;

//	@NotNull(message = "Equipment details are required")
//	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	private List<EquipmentDetails> equipmentDetails;

//	@NotNull(message = "Personal information is required")
//	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private PersonalInformation personalInfo;

//	@NotNull(message = "Payment details are required")
//	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private PaymentDetails paymentDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getRoomCost() {
		return roomCost;
	}

	public void setRoomCost(double roomCost) {
		this.roomCost = roomCost;
	}

	public double getEquipmentCost() {
		return equipmentCost;
	}

	public void setEquipmentCost(double equipmentCost) {
		this.equipmentCost = equipmentCost;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public RoomBookingDetails getRoomDetails() {
		return roomDetails;
	}

	public void setRoomDetails(RoomBookingDetails roomDetails) {
		this.roomDetails = roomDetails;
	}

	public List<EquipmentDetails> getEquipmentDetails() {
		return equipmentDetails;
	}

	public void setEquipmentDetails(List<EquipmentDetails> equipmentDetails) {
		this.equipmentDetails = equipmentDetails;
	}

	public PersonalInformation getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInformation personalInfo) {
		this.personalInfo = personalInfo;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	// Getters and Setters
	
	
}
