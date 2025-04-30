package com.ppk.topRepositry.formRepositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppk.topEntity.roomEntity.EquipmentDetails;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentDetails, Long> {

}