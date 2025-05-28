package com.ppk.topEntity.formsEntity;

import java.math.BigDecimal;

/**
 * Entity class for storing dependent details.
 */
public class DependentDetails {
    
    private Integer dependentId;
    private String userId;
    private String dependentName;
    private String relationship;
    private String idNumber;
    private String status;
    private BigDecimal fee;
    
    // Default constructor
    public DependentDetails() {
    }
    
    // Constructor with all fields
    public DependentDetails(Integer dependentId, String userId, String dependentName, 
                           String relationship, String idNumber, String status, BigDecimal fee) {
        this.dependentId = dependentId;
        this.userId = userId;
        this.dependentName = dependentName;
        this.relationship = relationship;
        this.idNumber = idNumber;
        this.status = status;
        this.fee = fee;
    }
    
    // Getters and Setters
    public Integer getDependentId() {
        return dependentId;
    }
    
    public void setDependentId(Integer dependentId) {
        this.dependentId = dependentId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getDependentName() {
        return dependentName;
    }
    
    public void setDependentName(String dependentName) {
        this.dependentName = dependentName;
    }
    
    public String getRelationship() {
        return relationship;
    }
    
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    
    public String getIdNumber() {
        return idNumber;
    }
    
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public BigDecimal getFee() {
        return fee;
    }
    
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    
    @Override
    public String toString() {
        return "DependentDetails{" +
                "dependentId=" + dependentId +
                ", userId='" + userId + '\'' +
                ", dependentName='" + dependentName + '\'' +
                ", relationship='" + relationship + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", status='" + status + '\'' +
                ", fee=" + fee +
                '}';
    }
} 