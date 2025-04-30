package com.ppk.topEntity.userEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_info")
class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "relationship", nullable = false)
    private String relationship;

    @Column(name = "is_oku")
    private boolean isOku;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    // Getters and setters
    // ...
}
