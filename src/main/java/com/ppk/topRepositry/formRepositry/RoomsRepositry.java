package com.ppk.topRepositry.formRepositry;

import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ppk.topEntity.roomEntity.RoomReservationFormEntity;

@Repository
public interface RoomsRepositry  extends JpaRepository<RoomReservationFormEntity, Long>{

//	public List<RoomReservationFormEntity> findByIsAvailableTrue(); // Temporarily commented out for diagnostics
//	public RoomReservationFormEntity findByIdAndIsAvailableTrue(Long id); // Temporarily commented out for diagnostics
//	@Query("SELECT r FROM RoomReservation r WHERE r.roomId = :roomId AND (r.startTime < :endTime AND r.endTime > :startTime)") 
//	List<RoomReservation> findReservationsForRoom(@Param("roomId") String roomId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

/* // Temporarily commented out for diagnostics due to RoomReservationFormEntity simplification	
	 @Query("SELECT r FROM RoomReservationFormEntity r WHERE " +
	           "(:labelName IS NULL OR r.labelName LIKE %:labelName%) " +
	           "AND (:capacity IS NULL OR r.capacity = :capacity) " +
	           "AND r.isAvailable = true " +  // Ensures only available rooms are returned
	           "AND (:price IS NULL OR r.price = :price)")
	    List<RoomReservationFormEntity> findByFilters(
	            @Param("labelName") String labelName,
	            @Param("capacity") Integer capacity,
	            @Param("price") Double price);
*/		
}
