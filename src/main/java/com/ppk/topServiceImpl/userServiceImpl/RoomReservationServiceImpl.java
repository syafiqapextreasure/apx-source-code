package com.ppk.topServiceImpl.userServiceImpl;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ppk.topEntity.errorEntity.RoomNotFoundException;
import com.ppk.topEntity.errorEntity.RoomReservationServiceException;
import com.ppk.topEntity.roomEntity.EquipmentDetails;
import com.ppk.topEntity.roomEntity.RoomBookingDetails;
import com.ppk.topEntity.roomEntity.RoomBookingSummary;
import com.ppk.topEntity.roomEntity.RoomReservationFormEntity;
import com.ppk.topRepositry.formRepositry.RoomBookingDetailsRepo;
import com.ppk.topRepositry.formRepositry.RoomsRepositry;
import com.ppk.topServiceImpl.formServiceImpl.RoomReservationService;

import javax.transaction.Transactional;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {
	private static final Logger logger = LoggerFactory.getLogger(RoomReservationServiceImpl.class);

	@Autowired
	private RoomsRepositry roomsRepositry;
	
	@Autowired
	private RoomBookingDetailsRepo roomBookingDetailsRepo;

	@Override
	public List<RoomReservationFormEntity> getAllAvailableRooms() throws RoomReservationServiceException {
		// TODO: Check if this should return all rooms or only available ones.
		// Currently, it implies all rooms by its name, but the original call was
		// findByIsAvailableTrue.
		// For diagnostics, we are simplifying, so using findAll() might be intended if
		// isAvailable is one of the problematic fields.
		// For now, matching the interface which implies it might have specific logic for availability
		try {
			logger.info("Fetching all available rooms (diagnostics - using findAll).");
			return roomsRepositry.findAll(); // Temporarily changed for diagnostics, should align with actual availability logic
		} catch (Exception e) {
			logger.error("Error in getAllAvailableRooms: {}", e.getMessage());
			throw new RoomReservationServiceException("Error fetching available rooms: " + e.getMessage());
		}
	}

	// BORANG TEMPAHAN BILIK second form room get details by id
	@Override
	public RoomReservationFormEntity getRoomDetailsById(Long id) {
		// TODO: Similar to getAllRooms, check if only available rooms should be fetched.
		// Original was findByIdAndIsAvailableTrue.
		Optional<RoomReservationFormEntity> roomOptional = roomsRepositry.findById(id); // Temporarily changed for diagnostics
		return roomOptional.orElse(null);
	}

	@Override
	public RoomBookingSummary calculateBookingSummary(RoomBookingDetails bookingDetails,
			List<EquipmentDetails> equipmentList) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isRoomAlreadyBooked(Long roomId, LocalDate startDate, LocalDate endDate, String time) {
	    // Check if any room booking exists for the specified room, date range, and time
	    return roomBookingDetailsRepo.existsByRoomIdAndDateRangeAndTime(roomId, startDate, endDate, time);
	}


	// BORANG TEMPAHAN BILIK second form room get details by id end

//	@Override 
//	public boolean isRoomAvailable(String roomId, LocalDateTime startTime, LocalDateTime endTime) { 
//		List<RoomReservation> existingReservations = reservationRepository.findReservationsForRoom(roomId, startTime, endTime);
//		for (RoomReservation reservation : existingReservations) { 
//			if (startTime.isBefore(reservation.getEndTime()) && endTime.isAfter(reservation.getStartTime())) { 
//				// There's an overlap with an existing reservation 
//				return false; } }
//		// No overlapping reservations 
//		return true; }
//			}
//		}
//	}

//	@Transactional 
//	@Override
//	public RoomReservationResponse reserveRoom(RoomReservationRequest request) throws RoomReservationServiceException {
//	    logger.info("Initiating room reservation process with request details: {}", request);
//	    
//	    // Check room availability
////	    boolean isAvailable = roomAvailabilityService.isRoomAvailable(request.getRoomId(), request.getStartTime(), request.getEndTime());
////	    if (!isAvailable) {
////	        logger.warn("Room is unavailable for the requested time period: {}", request);
////	        throw new RoomReservationServiceException("The room is not available for the requested time.");
////	    }
//	    
//	    // Create a new reservation entity
//	    RoomReservation reservation = new RoomReservation();
//	    reservation.setPatronId(request.getPatronId());
//	    reservation.setRoomId(request.getRoomId());
//	    reservation.setStartTime(request.getStartTime());
//	    reservation.setEndTime(request.getEndTime());
//	    reservation.setPurpose(request.getPurpose());
//	    
//	    try {
//	        // Persist the reservation details
//	        RoomReservation savedReservation = reservationRepository.save(reservation);
//	        logger.info("Room successfully reserved: {}", savedReservation);
//	        
//	        // Prepare response with reservation details
//	        RoomReservationResponse response = new RoomReservationResponse();
//	        response.setReservationId(savedReservation.getId());
//	        response.setPatronId(savedReservation.getPatronId());
//	        response.setRoomId(savedReservation.getRoomId());
//	        response.setStartTime(savedReservation.getStartTime());
//	        response.setEndTime(savedReservation.getEndTime());
//	        response.setStatus("Reserved");
//	        
//	        return response;
//	    } catch (Exception e) {
//	        logger.error("An error occurred while processing the room reservation.", e);
//	        throw new RoomReservationServiceException("An error occurred during the reservation process.");
//	    }
//	}

	// BORANG TEMPAHAN BILIK second form room
//	@Transactional
//	@Override
//	public RoomReservationResponse reserveRoom(RoomReservationRequest request) throws RoomReservationServiceException {
//		logger.info("Initiating room reservation process with request details: {}", request);
//
//		RoomReservationResponse response = new RoomReservationResponse();
//		try {
//			// Validate the request object
//			if (ObjectUtils.isEmpty(request)) {
//				logger.warn("Room reservation request is empty or invalid.");
//				response.setMessage("Form not filled");
//				response.setSuccess(false);
//				return response;
//			}
//
//			// Persist the reservation details
//			RoomReservationRequest savedRequest = this.roomsRepositry.save(request);
//			if (ObjectUtils.isEmpty(savedRequest)) {
//				logger.error("Failed to save room reservation request.");
//				response.setMessage("Failed to reserve the room due to an internal error.");
//				response.setSuccess(false);
//				return response;
//			}
//
//			logger.info("Room successfully reserved with ID: {}", savedRequest.getRoomId());
//
//			// Set success response
//			response.setMessage("Room reservation successful.");
//			response.setSuccess(true);
//			return response;
//
//		} catch (DataAccessException e) {
//			// Handle database-related issues
//			logger.error("Database error occurred while processing the room reservation: {}", e.getMessage());
//			throw new RoomReservationServiceException("Database error occurred during the reservation process.");
//		} catch (Exception e) {
//			// Handle other unexpected errors
//			logger.error("An unexpected error occurred while processing the room reservation.", e);
//			throw new RoomReservationServiceException("An unexpected error occurred during the reservation process.");
//		}
//	}

	// BORANG TEMPAHAN BILIK second form room end
	
	
	//BORANG TEMPAHAN BILIK 2nc form room  get details by id
	


}
