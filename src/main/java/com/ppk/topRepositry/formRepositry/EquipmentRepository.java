package com.ppk.topRepositry.formRepositry;

import java.util.List;
import java.util.Collections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ppk.topEntity.roomEntity.EquipmentDetails;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentDetails, Long> {
    
    List<EquipmentDetails> findByIsActiveTrue();
    
    @Query("SELECT e FROM EquipmentDetails e WHERE e.isActive = true AND e.isMultiunit = true")
    List<EquipmentDetails> findAllMultiunitActiveEquipment();
    
    @Query("SELECT e FROM EquipmentDetails e WHERE e.isActive = true AND e.isMultiunit = false")
    List<EquipmentDetails> findAllSingleunitActiveEquipment();
    
    @Query("SELECT e FROM EquipmentDetails e WHERE e.id IN :ids")
    List<EquipmentDetails> findAllByIds(List<Long> ids);
    
    // Use JPQL query instead of native SQL to be more database-agnostic
    // This safer approach will return an empty list when the join table doesn't exist
   // default List<EquipmentDetails> findAllByBookingId(Long bookingId) {
        // Return empty list to avoid errors with missing tables
     //   return Collections.emptyList();
    //}
    
    List<EquipmentDetails> findByBookingId(Long bookingId);
}