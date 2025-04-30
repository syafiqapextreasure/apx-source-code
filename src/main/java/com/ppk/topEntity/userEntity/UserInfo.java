package com.ppk.topEntity.userEntity;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ppj_user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "portal_user_id", nullable = false)
    private String portalUserId;

    @Column(name = "location_ppk_choice")
    private String locationPpkChoice;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ic_number", nullable = false)
    private String icNumber;

    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "nationality", nullable = false)
    private boolean nationality;

    @Column(name = "ethnicity")
    private String ethnicity;

    // Dependent information
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_info_id")
    private List<Dependent> dependents;

    // Correspondence Address
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    // Additional Info
    @Column(name = "family_ppj_staff")
    private boolean familyPpjStaff;

    @Column(name = "is_oku")
    private boolean isOku;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_info_id")
    private List<PaymentInfo> paymentInfo;

    // Getters and setters
    // ...
}
