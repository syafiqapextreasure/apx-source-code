-- SQL Schema for Room Booking System (MySQL)

-- Table: bookings 
-- Description: Stores information about room bookings
CREATE TABLE IF NOT EXISTS bookings (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Booking Number
    room_id INT, -- Room Number
    start_date DATE, -- Start date use room
    end_date DATE, -- Last date use room
    time VARCHAR(50), -- Time slot (like "10:00AM-11:00AM")
    attendees INT, -- Number of attendees
    status VARCHAR(20) DEFAULT 'Pending', -- Status booking (Approve / Pending / Cancel)
    cancelled BOOLEAN DEFAULT false, -- Whether booking is cancelled
    customer_name VARCHAR(255), -- Customer Name
    total_cost DECIMAL(10, 2) DEFAULT 0.00 -- Total cost of booking
);

-- Table: bookings_equipment
-- Description: Links bookings with the equipment used
CREATE TABLE IF NOT EXISTS bookings_equipment (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Booking Equipment ID
    booking_id INT, -- Booking ID
    equipment_id INT, -- Equipment Info
    units INT, -- Quantity unit of equipment
    FOREIGN KEY (booking_id) REFERENCES bookings(id),
    FOREIGN KEY (equipment_id) REFERENCES equipments(id) -- Assuming 'equipments' table exists
);

-- Table: bookings_payments
-- Description: Stores payment information for bookings
CREATE TABLE IF NOT EXISTS bookings_payments (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Booking Payment ID
    booking_id INT, -- Booking ID
    payment_method ENUM('Cash', 'Card', 'Transfer', 'Online'), -- Payment Method (example values)
    payment_type VARCHAR(50), -- Payment type
    amount DECIMAL(10, 2), -- Amount payment
    status ENUM('Paid', 'Unpaid', 'Refunded'), -- Status payment (example values)
    FOREIGN KEY (booking_id) REFERENCES bookings(id)
);

-- Table: equipments
-- Description: Stores information about available equipment
CREATE TABLE IF NOT EXISTS equipments (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Equipment ID
    equipment_name VARCHAR(255), -- Equipment Name
    multi_units ENUM('Yes', 'No'), -- Options can apply multi unit or not
    price DECIMAL(10, 2) -- Equipment price
); 