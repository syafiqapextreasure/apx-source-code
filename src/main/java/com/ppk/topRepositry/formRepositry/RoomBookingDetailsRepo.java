package com.ppk.topRepositry.formRepositry;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ppk.topEntity.roomEntity.RoomBookingDetails;
import com.ppk.topEntity.roomEntity.RoomReservationFormEntity;

@Repository
public interface RoomBookingDetailsRepo extends JpaRepository<RoomBookingDetails, Long>{
	  // Custom query to check for overlapping bookings
    @Query("SELECT COUNT(r) > 0 FROM RoomBookingDetails r WHERE r.roomId = :roomId AND " +
            "((r.startDate BETWEEN :startDate AND :endDate) OR " +
            "(r.endDate BETWEEN :startDate AND :endDate) OR " +
            "(r.startDate <= :startDate AND r.endDate >= :endDate)) AND " +
            "r.time = :time")
    boolean existsByRoomIdAndDateRangeAndTime(@Param("roomId") Long roomId,
                                              @Param("startDate") LocalDate startDate,
                                              @Param("endDate") LocalDate endDate,
                                              @Param("time") String time);

    // Find all bookings for a specific room
    List<RoomBookingDetails> findByRoomId(Long roomId);
	
}