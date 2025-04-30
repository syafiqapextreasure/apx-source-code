package com.ppk.topEntity.userEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dependent_info")
class Dependent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dependent_user_id", nullable = false)
    private String dependentUserId;

    @Column(name = "ic_number", nullable = false)
    private String icNumber;

    @Column(name = "relationship", nullable = false)
    private String relationship;

    @Column(name = "is_oku")
    private boolean isOku;

    // Getters and setters
    // ...
}
