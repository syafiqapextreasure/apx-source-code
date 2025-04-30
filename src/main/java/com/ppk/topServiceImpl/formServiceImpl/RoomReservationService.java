package com.ppk.topServiceImpl.formServiceImpl;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.List;

//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ppk.topEntity.errorEntity.RoomNotFoundException;
import com.ppk.topEntity.errorEntity.RoomReservationServiceException;
import com.ppk.topEntity.roomEntity.RoomBookingDetails;
import com.ppk.topEntity.roomEntity.RoomReservationFormEntity;

public interface RoomReservationService {
	public List<RoomReservationFormEntity> getAllAvailableRooms() throws RoomReservationServiceException;

	public RoomReservationFormEntity getRoomDetailsById(Long roomId)
			throws RoomNotFoundException, RoomReservationServiceException;

	public com.ppk.topEntity.roomEntity.RoomBookingSummary calculateBookingSummary(RoomBookingDetails bookingDetails,
			List<com.ppk.topEntity.roomEntity.EquipmentDetails> equipmentList);

	public boolean isRoomAlreadyBooked(Long roomId, LocalDate startDate, LocalDate endDate, String time);
}
