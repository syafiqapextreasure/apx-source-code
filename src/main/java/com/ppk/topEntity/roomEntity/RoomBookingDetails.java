package com.ppk.topEntity.roomEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class RoomBookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomId;        // Room ID  map with actual room entity later
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    
    private String time; 

   
    public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private int attendees;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getAttendees() {
		return attendees;
	}
	public void setAttendees(int attendees) {
		this.attendees = attendees;
	}
	public RoomBookingDetails(Long id, Long roomId, LocalDate startDate, LocalDate endDate, String time,
			int attendees) {
		super();
		this.id = id;
		this.roomId = roomId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.time = time;
		this.attendees = attendees;
	}

    
    
}


