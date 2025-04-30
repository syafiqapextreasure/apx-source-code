package com.ppk.topService.formService.roomService;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppk.topEntity.roomEntity.EquipmentDetails;
import com.ppk.topEntity.roomEntity.PersonalInformation;
import com.ppk.topEntity.roomEntity.RoomBookingDetails;
import com.ppk.topEntity.roomEntity.RoomReservationFormEntity;

public interface RoomService {
	public  List<RoomReservationFormEntity> getAllRooms();
    public RoomReservationFormEntity getRoomById(Long roomId);
    public RoomReservationFormEntity getRoomBEquipById(Long roomId);
    public RoomReservationFormEntity getRoomSummaryId(Long roomId);
    public RoomBookingDetails saveRoomDetails(RoomBookingDetails roomBookingDetails);
    public void saveSelectedEquipment(List<EquipmentDetails> equipmentList);
    public PersonalInformation savedUserDetails(PersonalInformation entity);
}
