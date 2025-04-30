package com.ppk.topEntity.roomEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class RoomBookingSummary {
    private String roomName;          // P11 Bilik Mesyurat
    private int attendees;            // 20 orang
    private double hourlyRate;        // 13.00 MYR
    private double durationHours;     // 1.0 (calculated as endTime - startTime)
    private double roomCost;          // 13.00 MYR

    private LocalDate bookingDate;    // 03-09-2024
    private LocalTime startTime;      // 10:00
    private LocalTime endTime;        // 11:00
    private String layout;            // Bilik Mesyuarat

    private List<EquipmentDetails> equipmentList; // List of equipment
    private double equipmentCost;    // Total equipment cost

    private double subtotal;         // Jumlah kecil: RoomCost + EquipmentCost
    private double tax;              // Cukal: Calculated as a percentage of subtotal
    private double total;            // Jumlah: Subtotal + Tax
    private double deposit;   
    // Deposit: 0.00 MYR (can be configured)
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getAttendees() {
		return attendees;
	}
	public void setAttendees(int attendees) {
		this.attendees = attendees;
	}
	public double getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public double getDurationHours() {
		return durationHours;
	}
	public void setDurationHours(double durationHours) {
		this.durationHours = durationHours;
	}
	public double getRoomCost() {
		return roomCost;
	}
	public void setRoomCost(double roomCost) {
		this.roomCost = roomCost;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public List<EquipmentDetails> getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(List<EquipmentDetails> equipmentList) {
		this.equipmentList = equipmentList;
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
    
    
    
}
