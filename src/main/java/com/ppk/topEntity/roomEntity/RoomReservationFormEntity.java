package com.ppk.topEntity.roomEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="rooms")
public class RoomReservationFormEntity {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id; // Auto-incremented id

	    @Column(nullable = false)
	    private Integer capacity; // Capacity field

	    @Column(nullable = false)
	    private Boolean isAvailable = true; // Availability field (bit(1) mapped to Boolean), initialized to true

	    @Column(nullable = false, name = "label_ame") // Corrected column name to match database
	    private String labelName = "Default Room"; // Label name field with default value

	    @Column(nullable = false, name = "label_name") // Add the other label column to fix the error
	    private String labelName2 = "Default Room"; // Same value as labelName

	    @Column(nullable = false)
	    private Double price = 0.0; // Price field with default value

	    @Column(nullable = false)
	    private String location = "Main Building"; // Location field with default value

	    @Column(nullable = false)
	    private String roomType = "Bilik Mesyuarat"; // Room type field with default value

	    @Column(length = 255)
	    private String imgName; // Image name field, can be null

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Integer getCapacity() {
			return capacity;
		}

		public void setCapacity(Integer capacity) {
			this.capacity = capacity;
		}

		public Boolean getIsAvailable() {
			return isAvailable;
		}

		public void setIsAvailable(Boolean isAvailable) {
			this.isAvailable = isAvailable;
		}

		public String getLabelName() {
			return labelName;
		}

		public void setLabelName(String labelName) {
			this.labelName = labelName;
			this.labelName2 = labelName; // Keep both fields in sync
		}

		public String getLabelName2() {
			return labelName2;
		}

		public void setLabelName2(String labelName2) {
			this.labelName2 = labelName2;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getRoomType() {
			return roomType;
		}

		public void setRoomType(String roomType) {
			this.roomType = roomType;
		}

		public String getImgName() {
			return imgName;
		}

		public void setImgName(String imgName) {
			this.imgName = imgName;
		}
	    
}
