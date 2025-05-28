package com.ppk.config;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    @Transactional
    public void initialize() {
        logger.info("Initializing database...");
        
        try {
            // Check if payment_breakdown column exists
            try {
                // Try to add the column (older MySQL syntax)
                jdbcTemplate.execute("ALTER TABLE lost_material_form ADD COLUMN payment_breakdown VARCHAR(255)");
                logger.info("Successfully added payment_breakdown column");
            } catch (Exception e) {
                // Column might already exist, which is fine
                logger.info("Column payment_breakdown might already exist: {}", e.getMessage());
            }
            
            // Add test data to list_borroed_materials if empty
            try {
                int count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM list_borroed_materials", Integer.class);
                
                if (count == 0) {
                    logger.info("Adding test borrowed books data...");
                    jdbcTemplate.execute(
                        "INSERT INTO list_borroed_materials (acquisition_number, book_title, book_price) " +
                        "VALUES ('BK12345', 'Introduction to Java Programming', 28.50), " +
                        "('BK67890', 'Database Systems', 32.75), " +
                        "('BK24680', 'Web Development with Spring Boot', 45.00)"
                    );
                    logger.info("Test data added successfully!");
                }
            } catch (Exception e) {
                logger.error("Error adding test data: {}", e.getMessage());
            }
        } catch (Exception e) {
            logger.error("Error adding payment_breakdown column: {}", e.getMessage());
        }
    }
} 