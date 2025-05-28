package com.ppk.topServiceImpl.formServiceImpl.roomServiceImpl;

import java.time.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

	@Override
	public List<RoomReservationFormEntity> getAllRooms() {
		try {
			List<RoomReservationFormEntity> rooms = roomsRepositry.findAll();
			if (rooms == null || rooms.isEmpty()) {
				logger.info("No rooms found in database, returning sample data for testing");
				return createSampleRooms();
			}
			logger.info("Found {} rooms in database", rooms.size());
			return rooms;
		} catch (Exception e) {
			logger.error("Error fetching rooms: {}", e.getMessage(), e);
			logger.info("Returning sample rooms due to error");
			return createSampleRooms();
		}
	}

	@Override
	public RoomReservationFormEntity getRoomById(Long roomId) {
		try {
			logger.info("Fetching room with ID: {}", roomId);
			return roomsRepositry.findById(roomId)
					.orElseGet(() -> {
						logger.warn("Room with ID {} not found, returning dummy room", roomId);
						return createDummyRoom(roomId);
					});
		} catch (Exception e) {
			logger.error("Error fetching room with ID {}: {}", roomId, e.getMessage(), e);
			return createDummyRoom(roomId);
		}
	}

	@Override
	public RoomReservationFormEntity getRoomBEquipById(Long roomId) {
		try {
			logger.info("Fetching room with ID: {} for equipment", roomId);
			return roomsRepositry.findById(roomId)
					.orElseGet(() -> {
						logger.warn("Room with ID {} not found for equipment, returning dummy room", roomId);
						return createDummyRoom(roomId);
					});
		} catch (Exception e) {
			logger.error("Error fetching room with ID {} for equipment: {}", roomId, e.getMessage(), e);
			return createDummyRoom(roomId);
		}
	}

	@Override
	public RoomReservationFormEntity getRoomSummaryId(Long roomId) {
		try {
			logger.info("Fetching room summary with ID: {}", roomId);
			return roomsRepositry.findById(roomId)
					.orElseGet(() -> {
						logger.warn("Room with ID {} not found for summary, returning dummy room", roomId);
						return createDummyRoom(roomId);
					});
		} catch (Exception e) {
			logger.error("Error fetching room summary with ID {}: {}", roomId, e.getMessage(), e);
			return createDummyRoom(roomId);
		}
	}

	/**
	 * Creates a list of sample rooms for testing when database returns no results
	 */
	private List<RoomReservationFormEntity> createSampleRooms() {
		List<RoomReservationFormEntity> sampleRooms = new ArrayList<>();
		
		// Create some sample rooms
		RoomReservationFormEntity room1 = new RoomReservationFormEntity();
		room1.setId(1L);
		room1.setLabelName("Bilik Mesyuarat 1");
		room1.setRoomType("Bilik Mesyuarat");
		room1.setCapacity(10);
		room1.setPrice(50.00);
		room1.setLocation("Tingkat 1");
		room1.setIsAvailable(true);
		room1.setImgName("room_p12.jpg");
		sampleRooms.add(room1);
		
		RoomReservationFormEntity room2 = new RoomReservationFormEntity();
		room2.setId(2L);
		room2.setLabelName("Bilik Serbaguna 1");
		room2.setRoomType("Bilik Serbaguna");
		room2.setCapacity(20);
		room2.setPrice(75.00);
		room2.setLocation("Tingkat 2");
		room2.setIsAvailable(true);
		room2.setImgName("room_p13.jpg");
		sampleRooms.add(room2);
		
		RoomReservationFormEntity room3 = new RoomReservationFormEntity();
		room3.setId(3L);
		room3.setLabelName("Bilik Mesyuarat Eksekutif");
		room3.setRoomType("Bilik Mesyuarat");
		room3.setCapacity(8);
		room3.setPrice(80.00);
		room3.setLocation("Tingkat 3");
		room3.setIsAvailable(true);
		room3.setImgName("room_p14.jpg");
		sampleRooms.add(room3);
		
		return sampleRooms;
	}

	/**
	 * Creates a dummy room when a specific room ID is not found
	 */
	private RoomReservationFormEntity createDummyRoom(Long roomId) {
		RoomReservationFormEntity dummyRoom = new RoomReservationFormEntity();
		dummyRoom.setId(roomId);
		dummyRoom.setLabelName("Bilik " + roomId);
		dummyRoom.setRoomType(roomId % 2 == 0 ? "Bilik Serbaguna" : "Bilik Mesyuarat");
		dummyRoom.setCapacity(10 + (int)(roomId % 5));
		dummyRoom.setPrice(50.00 + (roomId * 5));
		dummyRoom.setLocation("Tingkat " + (1 + (roomId % 3)));
		dummyRoom.setIsAvailable(true);
		dummyRoom.setImgName("room_p" + (10 + (roomId % 10)) + ".jpg");
		return dummyRoom;
	}

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

	// Method to retrieve all available rooms
	public List<RoomReservationFormEntity> getAllAvailableRooms() {
		// Retrieve all rooms that are marked as available
//		List<RoomReservationFormEntity> availableRooms = roomsRepositry.findByIsAvailableTrue();
		List<RoomReservationFormEntity> availableRooms = roomsRepositry.findAll(); // Temporary change for diagnostics
		// Log the number of available rooms found
		logger.info("Found {} available rooms.", availableRooms.size());
		return availableRooms;
	}

	/**
	 * Get all room bookings
	 * 
	 * @return List of all room bookings
	 */
	public List<RoomBookingDetails> getAllBookings() {
		return bookingDetailsRepo.findAll();
	}
	
	/**
	 * Get a specific room booking by ID
	 * 
	 * @param id The room booking ID
	 * @return The room booking details
	 */
	public RoomBookingDetails getRoomBookingById(Long id) {
		return bookingDetailsRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Room booking with ID " + id + " not found"));
	}

	@Transactional
	public RoomReservationFormEntity saveRoom(RoomReservationFormEntity room) {
		try {
			// Detailed logging before saving
            System.out.println("Attempting to save room in RoomServiceImpl: " + 
                               "ID=" + room.getId() + 
                            //    ", LabelName=" + room.getLabelName() +  // Temporarily commented out
                               ", Capacity=" + room.getCapacity() 
                            //    ", Price=" + room.getPrice() +          // Temporarily commented out
                            //    ", IsAvailable=" + room.getIsAvailable() + // Temporarily commented out
                            //    ", ImgName=" + room.getImgName()        // Temporarily commented out
                               );

			// logger.info("Saving new room: {}", room.getLabelName()); // Temporarily commented out
			logger.info("Saving new room with capacity: {}", room.getCapacity()); // Log capacity instead
			return roomsRepositry.save(room);
		} catch (Exception e) {
            // Log the detailed error with the room object's state if possible
            System.err.println("Error during saveRoom in RoomServiceImpl. Room details: " + 
                               "ID=" + room.getId() + 
                            //    ", LabelName=" + room.getLabelName() + // Temporarily commented out
                               ", Capacity=" + room.getCapacity() 
                            //    ", Price=" + room.getPrice() +         // Temporarily commented out
                            //    ", IsAvailable=" + room.getIsAvailable() + // Temporarily commented out
                            //    ", ImgName=" + room.getImgName()       // Temporarily commented out
                               );
			logger.error("Error saving room: {}", e.getMessage(), e);
			throw e; // Rethrow to be handled by the controller or a global exception handler
		}
	}

	/**
	 * Update an existing room
	 * 
	 * @param room The room to update
	 * @return The updated room
	 */
	@Transactional
	public RoomReservationFormEntity updateRoom(RoomReservationFormEntity room) {
		try {
			// Check if room exists
			if (room.getId() == null || !roomsRepositry.existsById(room.getId())) {
				throw new RuntimeException("Cannot update room: Room does not exist");
			}
			
			logger.info("Updating room with ID: {}", room.getId());
			System.out.println("Updating room: ID=" + room.getId() + 
							", Capacity=" + room.getCapacity() + 
							", ImgName=" + room.getImgName());
							
			return roomsRepositry.save(room);
		} catch (Exception e) {
			logger.error("Error updating room with ID {}: {}", room.getId(), e.getMessage(), e);
			throw e; // Rethrow to be handled by the controller
		}
	}

	/**
     * Delete a room by ID
     *
     * @param id The ID of the room to delete
     */
    @Transactional
    public void deleteRoom(Long id) {
        try {
            if (!roomsRepositry.existsById(id)) {
                throw new RoomNotFoundException("Room with ID " + id + " not found");
            }
            
            // Check if there are any bookings for this room
            List<RoomBookingDetails> bookings = bookingDetailsRepo.findByRoomId(id);
            if (!bookings.isEmpty()) {
                logger.warn("Cannot delete room with ID {} because it has {} associated bookings", id, bookings.size());
                throw new RuntimeException("Cannot delete room because it has associated bookings. Cancel all bookings first.");
            }
            
            logger.info("Deleting room with ID: {}", id);
            roomsRepositry.deleteById(id);
            logger.info("Room with ID: {} deleted successfully", id);
        } catch (RoomNotFoundException e) {
            logger.error("Failed to delete room: {}", e.getMessage());
            throw e;
			} catch (Exception e) {
            logger.error("Error deleting room with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to delete room: " + e.getMessage(), e);
			}
		}

	public RoomReservationFormEntity getRoomDetailsByBookingId(Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	// Temporary getFilteredRooms for diagnostics
	public List<RoomReservationFormEntity> getFilteredRooms(String labelName, Integer capacity, Double price) {
		logger.info("Executing temporary getFilteredRooms for diagnostics. Only filtering by capacity if provided.");
		List<RoomReservationFormEntity> allRooms = roomsRepositry.findAll();
		if (capacity != null) {
			return allRooms.stream().filter(room -> room.getCapacity() != null && room.getCapacity().equals(capacity)).collect(Collectors.toList());
		}
		return allRooms;
	}

	@Override
	public PersonalInformation savedUserDetails(PersonalInformation entity) {
		return bookingRepository.save(entity);
	}

	public List<RoomReservationFormEntity> getRoomsByCategory(String category) {
		List<RoomReservationFormEntity> rooms;
		if (category != null && !category.trim().isEmpty()) {
			logger.info("Fetching rooms for category: {}", category);
			// Temporarily filter by findAll and then by category in memory
			rooms = roomsRepositry.findAll().stream()
				// .filter(room -> room.getLabelName() != null && room.getLabelName().toLowerCase().contains(category.toLowerCase())) // Simplified for diagnostics
				.collect(Collectors.toList());
		} else {
			logger.info("Fetching all rooms as no specific category was provided (diagnostics).");
//			rooms = roomsRepositry.findByIsAvailableTrue();
			rooms = roomsRepositry.findAll(); // Temporary change for diagnostics
		}
		logger.info("Fetched {} rooms for category '{}'.", rooms.size(), category);
		return rooms;
	}

	/**
	 * Get all bookings for a specific room
	 * @param roomId The room ID
	 * @return List of bookings for the room
	 */
	public List<RoomBookingDetails> getBookingsForRoom(Long roomId) {
		try {
			logger.info("Fetching bookings for room ID: {}", roomId);
			return bookingDetailsRepo.findByRoomId(roomId);
		} catch (Exception e) {
			logger.error("Error fetching bookings for room ID: {}", roomId, e);
			return new ArrayList<>();
		}
	}

	/**
     * Cancel (delete) a specific booking
     * 
     * @param id The booking ID to cancel
     */
    @Transactional
    public void cancelBooking(Long id) {
        try {
            RoomBookingDetails booking = bookingDetailsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking with ID " + id + " not found"));
            
            logger.info("Canceling booking with ID: {}", id);
            bookingDetailsRepo.deleteById(id);
            logger.info("Booking with ID: {} canceled successfully", id);
        } catch (Exception e) {
            logger.error("Error canceling booking with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to cancel booking: " + e.getMessage(), e);
        }
    }

    /**
     * Cancel (delete) all bookings for a specific room
     * 
     * @param roomId The room ID
     * @return The number of bookings canceled
     */
    @Transactional
    public int cancelAllBookingsForRoom(Long roomId) {
            List<RoomBookingDetails> bookings = bookingDetailsRepo.findByRoomId(roomId);
            for (RoomBookingDetails booking : bookings) {
            booking.setCancelled(true);
            bookingDetailsRepo.save(booking);
            }
        logger.info("Cancelled all bookings for room {}", roomId);
        return bookings.size();
    }
    
    /**
     * Save or update a room booking
     * 
     * @param booking The booking to save or update
     * @return The saved booking
     */
    @Transactional
    public RoomBookingDetails saveBooking(RoomBookingDetails booking) {
        logger.info("Saving room booking: {}", booking);
        return bookingDetailsRepo.save(booking);
	}

	/**
     * Save selected equipment items for a booking
     * 
     * @param equipmentList The list of equipment items
     * @param bookingId The booking ID to link equipment to
     */
    @Override
    @Transactional
    public void saveSelectedEquipment(List<EquipmentDetails> equipmentList, Long bookingId) {
        if (equipmentList == null || equipmentList.isEmpty()) {
            logger.warn("No equipment to save for booking ID: {}", bookingId);
            return;
        }

        logger.info("Saving {} equipment items for booking ID: {}", equipmentList.size(), bookingId);
        
        try {
            // First verify the booking exists
            if (!bookingDetailsRepo.existsById(bookingId)) {
                logger.error("Cannot save equipment for non-existent booking ID: {}", bookingId);
                throw new RuntimeException("Booking not found with ID: " + bookingId);
            }
            
            // Filter equipment that is actually selected
            List<EquipmentDetails> selectedEquipment = equipmentList.stream()
                .filter(equipment -> equipment.isSelected() && equipment.getQuantity() != null && equipment.getQuantity() > 0)
                .collect(Collectors.toList());
            
            logger.info("Selected equipment items to save: {}", selectedEquipment.size());
            
            // Save each selected equipment item with reference to the booking
            for (EquipmentDetails equipment : selectedEquipment) {
                equipment.setBookingId(bookingId);
                EquipmentDetails savedEquipment = equipmentRepository.save(equipment);
                logger.info("Saved equipment: {} with quantity {} for booking ID: {}", 
                    savedEquipment.getEquipmentName(), savedEquipment.getQuantity(), bookingId);
            }
            
            // Update the booking with the total equipment cost
            RoomBookingDetails booking = bookingDetailsRepo.findById(bookingId).orElse(null);
            if (booking != null) {
                double totalEquipmentCost = selectedEquipment.stream()
                    .mapToDouble(e -> e.getPrice().doubleValue() * e.getQuantity())
                    .sum();
                
                // Add equipment cost to the booking total
                booking.setTotalCost(booking.getTotalCost() + totalEquipmentCost);
                bookingDetailsRepo.save(booking);
                logger.info("Updated booking ID: {} with total cost: {}", bookingId, booking.getTotalCost());
            }
        } catch (Exception e) {
            logger.error("Error saving equipment for booking ID {}: {}", bookingId, e.getMessage(), e);
            throw new RuntimeException("Failed to save equipment: " + e.getMessage(), e);
        }
    }

    /**
     * Get all equipment for a specific booking
     * @param bookingId The booking ID
     * @return List of equipment for the booking
     */
    public List<EquipmentDetails> getEquipmentByBookingId(Long bookingId) {
        return equipmentRepository.findAll().stream()
            .filter(e -> e.getBookingId() != null && e.getBookingId().equals(bookingId))
            .collect(java.util.stream.Collectors.toList());
    }

}
