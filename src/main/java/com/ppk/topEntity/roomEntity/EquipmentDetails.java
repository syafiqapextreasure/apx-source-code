package com.ppk.topEntity.roomEntity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents equipment that can be booked with a room, like chairs, tables, projectors, etc.
 */
@Entity
@Table(name = "equipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDetails {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "equipment_name", nullable = false)
    private String equipmentName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    
    @Column(name = "quantity_available")
    private Integer quantityAvailable;
    
    @Column(name = "is_multiunit")
    private Boolean isMultiunit = true;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    // Fields for tracking bookings - transient fields not stored in database
    @Column(name = "quantity")
    private Integer quantity;
    
    @Transient
    private Boolean selected;
    
    @Column(name = "booking_id")
    private Long bookingId;
    
    // Constructor for the existing code that uses this format
    public EquipmentDetails(Long id, String equipmentName, Integer quantity, Double price, Boolean selected) {
		this.id = id;
		this.equipmentName = equipmentName;
		this.quantity = quantity;
        this.price = price != null ? new BigDecimal(price) : null;
		this.selected = selected;
	}
    
    // Full constructor for all fields
    public EquipmentDetails(Long id, String equipmentName, String description, BigDecimal price, 
                           Integer quantityAvailable, Boolean isMultiunit, Boolean isActive, 
                           Integer quantity, Boolean selected) {
		this.id = id;
		this.equipmentName = equipmentName;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.isMultiunit = isMultiunit;
        this.isActive = isActive;
		this.quantity = quantity;
		this.selected = selected;
	}
    
    // Helper methods
    public BigDecimal getTotalPrice() {
        if (price == null || quantity == null) {
            return BigDecimal.ZERO;
        }
        return price.multiply(new BigDecimal(quantity));
    }
    
    // Add these methods to fix compilation errors
    public boolean isSelected() {
        return selected != null && selected;
    }
    
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    
    public Long getBookingId() {
        return this.bookingId;
    }
}

