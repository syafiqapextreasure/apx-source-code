package com.ppk.topRepositry.formRepositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppk.topEntity.roomEntity.PersonalInformation;
import com.ppk.topEntity.roomEntity.RoomReservationFormEntity;

@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long> {

	public PersonalInformation save(PersonalInformation entity);

	PersonalInformation findByBookingId(Long bookingId);
}
