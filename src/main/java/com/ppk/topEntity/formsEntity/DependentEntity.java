package com.ppk.topEntity.formsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "depedentreg")
public class DependentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dependentId")
    private Integer dependentId;

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "idPengguna")
    private String idPengguna;

    @Column(name = "nokPTanggungan")
    private String nokPTanggungan;

    @Column(name = "hubungan")
    private String hubungan;

    @Column(name = "statusOKU")
    private String statusOKU;

    @Column(name = "harga")
    private BigDecimal harga;

    // Constructors
    public DependentEntity() {
        // Default constructor
    }

    // Getters and Setters
    public Integer getDependentId() {
        return dependentId;
    }

    public void setDependentId(Integer dependentId) {
        this.dependentId = dependentId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNokPTanggungan() {
        return nokPTanggungan;
    }

    public void setNokPTanggungan(String nokPTanggungan) {
        this.nokPTanggungan = nokPTanggungan;
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public String getStatusOKU() {
        return statusOKU;
    }

    public void setStatusOKU(String statusOKU) {
        this.statusOKU = statusOKU;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "DependentEntity{" +
                "dependentId=" + dependentId +
                ", loginId='" + loginId + '\'' +
                ", idPengguna='" + idPengguna + '\'' +
                ", nokPTanggungan='" + nokPTanggungan + '\'' +
                ", hubungan='" + hubungan + '\'' +
                ", statusOKU='" + statusOKU + '\'' +
                ", harga=" + harga +
                '}';
    }
} 