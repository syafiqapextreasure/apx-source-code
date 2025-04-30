package com.ppk.topServiceImpl.formServiceImpl.roomServiceImpl;

import java.time.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppk.topEntity.errorEntity.RoomNotFoundException;
import com.ppk.topEntity.roomEntity.BookingSummary;
import com.ppk.topEntity.roomEntity.EquipmentDetails;
import com.ppk.topEntity.roomEntity.PersonalInformation;
import com.ppk.topEntity.roomEntity.RoomBookingDetails;
import com.ppk.topEntity.roomEntity.RoomBookingSummary;
import com.ppk.topEntity.roomEntity.RoomReservationFormEntity;
import com.ppk.topRepositry.formRepositry.EquipmentRepository;
import com.ppk.topRepositry.formRepositry.PersonalInformationRepository;
import com.ppk.topRepositry.formRepositry.RoomBookingDetailsRepo;
import com.ppk.topRepositry.formRepositry.RoomsRepositry;
import com.ppk.topService.formService.roomService.RoomService;

import javax.transaction.Transactional;

@Service
public class RoomServiceImpl implements RoomService {
	private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

	@Autowired
	private RoomsRepositry roomsRepositry;

	@Autowired
	private RoomBookingDetailsRepo bookingDetailsRepo;

	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private PersonalInformationRepository bookingRepository;

	private static final double TAX_RATE = 0.00; // 0% tax
	private static final double DEFAULT_DEPOSIT = 0.00;

	// calculation logic to summary
//    public BookingSummary calculateBookingSummary(BookingSummary bookingSummary) {
//        // Calculate room cost
//        double roomCost = bookingSummary.getHourlyRate() * bookingSummary.getDurationHours();
//
//        // Calculate equipment cost
//        double equipmentCost = bookingSummary.getEquipmentList().stream()
//            .filter(EquipmentDetails::isSelected)
//            .mapToDouble(e -> e.getPrice() * e.getQuantity())
//            .sum();
//
//        // Calculate subtotal
//        double subtotal = roomCost + equipmentCost;
//
//        // Tax calculation (assuming 0% for now)
//        double tax = 0.0;
//
//        // Calculate total
//        double total = subtotal + tax;
//
//        // Deposit (optional)
//        double deposit = 0.0;
//
//        // Create and populate BookingSummary
//        BookingSummary summary = new BookingSummary();
//        summary.setRoomCost(roomCost);
//        summary.setEquipmentCost(equipmentCost);
//        summary.setSubtotal(subtotal);
//        summary.setTax(tax);
//        summary.setTotal(total);
//        summary.setDeposit(deposit);
//
//        return summary;
//    }

	public List<RoomReservationFormEntity> getAllRooms() {
		logger.info("Fetching all rooms from the database.");
		List<RoomReservationFormEntity> rooms = roomsRepositry.findByIsAvailableTrue();
		System.err.println("Fetched all rooms " + rooms);
		if (rooms.isEmpty()) {
			logger.warn("No rooms found in the database.");
		} else {
			logger.info("Found {} rooms in the database.", rooms.size());
		}
		return rooms;
	}

	@Override
	public RoomReservationFormEntity getRoomById(Long roomId) {
		try {
			// Try to fetch the room by ID from the database
			RoomReservationFormEntity room = roomsRepositry.findById(roomId)
					.orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found"));

			// Log successful retrieval of the room
			logger.info("Successfully fetched room with ID {}", roomId);

			return room;
		} catch (RoomNotFoundException e) {
			// Log the error if the room is not found
			logger.error("Room with ID {} not found", roomId);
			throw e; // Rethrow the exception to be handled by the controller or higher layer
		} catch (Exception e) {
			// Log any other unexpected exceptions
			logger.error("An unexpected error occurred while fetching room with ID {}", roomId, e);
			throw new RuntimeException("An error occurred while fetching the room.", e);
		}
	}

	@Override
	public RoomReservationFormEntity getRoomBEquipById(Long roomId) {
		try {
			// Try to fetch the room by ID from the database
			RoomReservationFormEntity room = roomsRepositry.findById(roomId)
					.orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found"));

			// Log successful retrieval of the room
			logger.info("Successfully fetched room with ID {}", roomId);

			return room;
		} catch (RoomNotFoundException e) {
			// Log the error if the room is not found
			logger.error("Room with ID {} not found", roomId);
			throw e; // Rethrow the exception to be handled by the controller or higher layer
		} catch (Exception e) {
			// Log any other unexpected exceptions
			logger.error("An unexpected error occurred while fetching room with ID {}", roomId, e);
			throw new RuntimeException("An error occurred while fetching the room.", e);
		}
	}

	@Override
	public RoomReservationFormEntity getRoomSummaryId(Long roomId) {
		return null;
	}

//	// room booking steps 1 to choose time and date with attendence
//	public RoomBookingDetails saveRoomDetails(RoomBookingDetails roomBookingDetails) {
//		try {
//			logger.info("Checking if the room is already booked for the date range: {} to {} and time: {}",
//					roomBookingDetails.getStartDate(), roomBookingDetails.getEndDate(), roomBookingDetails.getTime());
//
//			// Check if a room is already booked for the given date range and time
//			boolean isRoomBooked = bookingDetailsRepo.existsByRoomIdAndDateRangeAndTime(roomBookingDetails.getRoomId(),
//					roomBookingDetails.getStartDate(), roomBookingDetails.getEndDate(), roomBookingDetails.getTime());
//
//			if (isRoomBooked) {
//				logger.warn("Room is already booked for the given date range and time");
//				throw new RuntimeException("The room is already booked for this date range and time.");
//			}
//
//			// Save the new room booking if no booking exists for the same date range and
//			// time
//			logger.info("Saving room booking details for room: {}", roomBookingDetails.getRoomId());
//			return bookingDetailsRepo.save(roomBookingDetails);
//
//		} catch (Exception e) {
//			logger.error("Error while saving room booking details for room: {}", roomBookingDetails.getRoomId(), e);
//			throw new RuntimeException("Failed to save room booking details");
//		}
//	}

	// room booking steps 1 to choose time and date with attendence
	public RoomBookingDetails saveRoomDetails(RoomBookingDetails roomBookingDetails) {
		try {
			logger.info("Checking if the room is already booked for the date range: {} to {} and time: {}",
					roomBookingDetails.getStartDate(), roomBookingDetails.getEndDate(), roomBookingDetails.getTime());

			// Check if a room is already booked for the given date range and time
		
			  boolean isRoomBooked =
			  bookingDetailsRepo.existsByRoomIdAndDateRangeAndTime(roomBookingDetails.
			  getRoomId(), roomBookingDetails.getStartDate(),
			  roomBookingDetails.getEndDate(), roomBookingDetails.getTime());
			  
			  if (isRoomBooked) {
			  logger.warn("Room is already booked for the given date range and time");
			  throw new
			  RuntimeException("The room is already booked for this date range and time.");
			  }
			

			// Save the new room booking if no booking exists for the same date range and
			// time
			logger.info("Saving room booking details for room: {}", roomBookingDetails.getRoomId());
			return bookingDetailsRepo.save(roomBookingDetails);

		} catch (Exception e) {
			logger.error("Error while saving room booking details for room: {}", roomBookingDetails.getRoomId(), e);
			throw new RuntimeException("Failed to save room booking details");
		}
	}

	public boolean isRoomAlreadyBooked(Long roomId, LocalDate startDate, LocalDate endDate, String time) {
		// Check if any room booking exists for the specified room, date range, and time
		return bookingDetailsRepo.existsByRoomIdAndDateRangeAndTime(roomId, startDate, endDate, time);
	}

//	  @Transactional
//	    public RoomBookingDetails saveRoomDetails(RoomBookingDetails roomBookingDetails) {
//	        try {
//	            logger.info("Saving room booking details for room: {}", roomBookingDetails.getId());
//	            return bookingDetailsRepo.save(roomBookingDetails);
//	        } catch (Exception e) {
//	            logger.error("Error while saving room booking details for room: {}", roomBookingDetails.getId(), e);
//	            throw new RuntimeException("Failed to save room booking details");
//	        }
//	    }
//	  

//	public RoomBookingSummary calculateBookingSummary(RoomReservationFormEntity bookingDetails,
//			BookingSummary bookingSummary) {
//		    RoomBookingSummary summary = new RoomBookingSummary();
////            
////		    // Room Details
//		    summary.setRoomName(bookingDetails.getLabelName());
//		    summary.setAttendees(bookingSummary.getRoomDetails().getAttendees());
//		    summary.setHourlyRate(bookingDetails.getHourlyRate());

//		    // Calculate Duration
////		    double duration = Duration.between(bookingDetails.getStartTime(), bookingDetails.getEndTime()).toMinutes() / 60.0;
////		    summary.setDurationHours(duration);
//
//		    // Calculate Room Cost
//		    double roomCost = summary.getHourlyRate() * duration;
//		    summary.setRoomCost(roomCost);
//
//		    // Equipment Costs
//		    double equipmentCost = equipmentList.stream()
//		        .filter(EquipmentDetails::isSelected) // Only selected items
//		        .mapToDouble(equipment -> {
//		            double cost = equipment.getPrice();
//		            if (equipment.getEquipmentName().equalsIgnoreCase("Komputer")) {
//		                cost *= duration; // Hourly cost for "Komputer"
//		            }
//		            return cost * equipment.getQuantity();
//		        }).sum();
//		    summary.setEquipmentCost(equipmentCost);
//
//		    // Subtotal
//		    double subtotal = roomCost + equipmentCost;
//		    summary.setSubtotal(subtotal);
//
//		    // Tax (Assume 0% for now)
//		    double tax = 0.0; // Add logic here if tax is applicable
//		    summary.setTax(tax);
//
//		    // Total
//		    double total = subtotal + tax;
//		    summary.setTotal(total);
//
//		    // Deposit (Optional)
//		    summary.setDeposit(0.0);
//
//		    // Set Booking Date, Start Time, End Time
//		    summary.setBookingDate(bookingDetails.getStartDate());
//		    summary.setStartTime(bookingDetails.getStartTime());
//		    summary.setEndTime(bookingDetails.getEndTime());
//		    summary.setLayout(bookingDetails.getLayout());
//
//		    summary.setEquipmentList(equipmentList); // Include the equipment list for display
//
//		    return summary;
//		}
//
//		return null;
//
//	}

	@Override
	public void saveSelectedEquipment(List<EquipmentDetails> equipmentList) {
		if (equipmentList == null) {
			logger.warn("Equipment list is null");
			return;
		}

		if (equipmentList.isEmpty()) {
			logger.warn("Equipment list is empty");
			return;
		}

		logger.info("Processing {} equipment items for saving", equipmentList.size());

		int savedCount = 0;
		for (EquipmentDetails equipment : equipmentList) {
			try {
				if (equipment != null && equipment.isSelected() && equipment.getQuantity() > 0) {
					logger.info("Saving equipment: {}", equipment.getEquipmentName());

					// Set bookingId to null to avoid database column issue
					equipment.setBookingId(null);

					equipmentRepository.save(equipment);
					savedCount++;
				} else if (equipment != null) {
					logger.debug("Skipping equipment: {} (selected={}, quantity={})", equipment.getEquipmentName(),
							equipment.isSelected(), equipment.getQuantity());
				}
			} catch (Exception e) {
				logger.error("Failed to save equipment: {}", equipment != null ? equipment.getEquipmentName() : "null",
						e);
			}
		}

		logger.info("Successfully saved {} out of {} equipment items", savedCount, equipmentList.size());
	}

	// filter room

	public List<RoomReservationFormEntity> getFilteredRooms(String labelName, Integer capacity, Double price) {
		// Proceed to query, but force the query to only return available rooms
		return roomsRepositry.findByFilters(labelName, capacity, price);
	}

	@Override
	public PersonalInformation savedUserDetails(PersonalInformation entity) {
		return bookingRepository.save(entity);

	}

	/**
	 * Get all bookings for a specific room
	 * 
	 * @param roomId The room ID
	 * @return List of room booking details
	 */
	public List<RoomBookingDetails> getBookingsForRoom(Long roomId) {
		return bookingDetailsRepo.findByRoomId(roomId);
	}

	/**
	 * Get all available rooms
	 * 
	 * @return List of available rooms
	 */
	public List<RoomReservationFormEntity> getAllAvailableRooms() {
		return roomsRepositry.findByIsAvailableTrue();
	}

}
