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
	    private Boolean isAvailable; // Availability field (bit(1) mapped to Boolean)

	    @Column(nullable = false, name = "label_ame")
	    private String labelName; // Label name field

	    @Column(nullable = false)
	    private Double price; // Price field

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
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public String getImgName() {
			return imgName;
		}

		public void setImgName(String imgName) {
			this.imgName = imgName;
		}
	    
	    
	    
}
