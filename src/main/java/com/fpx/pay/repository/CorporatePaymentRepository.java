package com.fpx.pay.repository;

import com.fpx.pay.model.CorporatePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CorporatePaymentRepository extends JpaRepository<CorporatePayment, Long> {
    Optional<CorporatePayment> findBySellerOrderNo(String sellerOrderNo);
    
    @Query("SELECT cp FROM CorporatePayment cp WHERE cp.status = 'PENDING' AND cp.expiryDate < :now")
    List<CorporatePayment> findExpiredPayments(LocalDateTime now);
} 