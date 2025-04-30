package com.ppk.topService.formService.roomService;

import com.ppk.topEntity.roomEntity.EquipmentDetails;
import com.ppk.topEntity.roomEntity.PersonalInformation;
import com.ppk.topEntity.roomEntity.RoomBookingDetails;
import java.util.List;

public interface RoomBookingTransactionService {
    /**
     * Handles the complete room booking process in a single transaction.
     * All operations (room booking, equipment selection, and personal information)
     * are executed atomically - either all succeed or all fail.
     *
     * @param roomBookingDetails The room booking information
     * @param equipmentList The selected equipment for the booking
     * @param personalInfo The user's personal information
     * @return The saved room booking details
     * @throws RuntimeException if any part of the booking process fails
     */
    RoomBookingDetails processCompleteBooking(
        RoomBookingDetails roomBookingDetails,
        List<EquipmentDetails> equipmentList,
        PersonalInformation personalInfo
    );
} 