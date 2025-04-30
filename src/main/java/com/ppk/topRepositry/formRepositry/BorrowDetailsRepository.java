package com.ppk.topRepositry.formRepositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppk.topEntity.formsEntity.BorrowDetails;

@Repository
public interface BorrowDetailsRepository extends JpaRepository<BorrowDetails, Long> {
    // Custom queries if needed
}

