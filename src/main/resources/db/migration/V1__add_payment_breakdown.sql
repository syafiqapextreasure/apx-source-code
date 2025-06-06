-- Add payment_breakdown column to lost_material_form table
ALTER TABLE lost_material_form ADD COLUMN payment_breakdown VARCHAR(255);

-- Update existing records with placeholder breakdown details
UPDATE lost_material_form 
SET payment_breakdown = CONCAT('Book: RM', (total_payment * 0.7), 
                             ' + Late Fee: RM', (total_payment * 0.2), 
                             ' + Processing: RM', (total_payment * 0.1), 
                             ' = RM', total_payment)
WHERE payment_breakdown IS NULL; 