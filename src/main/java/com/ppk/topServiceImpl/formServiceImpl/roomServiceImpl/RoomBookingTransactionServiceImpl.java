package com.ppk.topServiceImpl.formServiceImpl.roomServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppk.topEntity.roomEntity.EquipmentDetails;
import com.ppk.topEntity.roomEntity.PersonalInformation;
import com.ppk.topEntity.roomEntity.RoomBookingDetails;
import com.ppk.topRepositry.formRepositry.EquipmentRepository;
import com.ppk.topRepositry.formRepositry.PersonalInformationRepository;
import com.ppk.topRepositry.formRepositry.RoomBookingDetailsRepo;
import com.ppk.topService.formService.roomService.RoomBookingTransactionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class RoomBookingTransactionServiceImpl implements RoomBookingTransactionService {
    
    private static final Logger logger = LoggerFactory.getLogger(RoomBookingTransactionServiceImpl.class);
    
    @Autowired
    private RoomBookingDetailsRepo roomBookingDetailsRepo;
    
    @Autowired
    private EquipmentRepository equipmentRepository;
    
    @Autowired
    private PersonalInformationRepository personalInfoRepository;

    @Override
    @Transactional
    public RoomBookingDetails processCompleteBooking(
            RoomBookingDetails roomBookingDetails,
            List<EquipmentDetails> equipmentList,
            PersonalInformation personalInfo) {
        
        try {
            logger.info("Starting room booking transaction for room ID: {}", roomBookingDetails.getRoomId());
            
            // 1. Save room booking details
            RoomBookingDetails savedBooking = roomBookingDetailsRepo.save(roomBookingDetails);
            logger.info("Successfully saved room booking details with ID: {}", savedBooking.getId());
            
            // 2. Save equipment details
            if (equipmentList != null && !equipmentList.isEmpty()) {
                for (EquipmentDetails equipment : equipmentList) {
                    if (equipment.isSelected() && equipment.getQuantity() > 0) {
                        // Set the reference to the booking
                        equipment.setBookingId(savedBooking.getId());
                        equipmentRepository.save(equipment);
                    }
                }
                logger.info("Successfully saved {} equipment items", equipmentList.size());
            }
            
            // 3. Save personal information
            if (personalInfo != null) {
                // Set the reference to the booking
                personalInfo.setBookingId(savedBooking.getId());
                PersonalInformation savedPersonalInfo = personalInfoRepository.save(personalInfo);
                logger.info("Successfully saved personal information for user: {}", savedPersonalInfo.getName());
            } else {
                throw new IllegalArgumentException("Personal information cannot be null");
            }
            
            return savedBooking;
            
        } catch (Exception e) {
            logger.error("Error during room booking transaction", e);
            // The @Transactional annotation will automatically rollback on exception
            throw new RuntimeException("Failed to complete room booking transaction: " + e.getMessage(), e);
        }
    }
} 