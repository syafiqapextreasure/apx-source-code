package com.ppk.topEntity.formsEntity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserMembership")
public class UserMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idPengguna;
    private String alamat;
    private LocalDate tarikhDidaftarkan;
    private LocalDate tarikhTamatKeahlian;
    private String noTelefon;
    private String emel;

    // Member Information
    private int bilanganPinjaman;
    private LocalDate tarikhPinjamanTerakhir;
    private int bahanLewatPulang;
    private LocalDate tarikhPemulanganTerakhir;
    private int bilanganBahanHilang;
    private LocalDate tarikhPembaharuanTerakhir;
    private int bilanganPenggantungan;
    private double jumlahTertunggak;
    private int bahanPinjamanTahunIni;
    private double jumlahBayaran;

    // Personal Information
    private String idKumpulan;
    private String jabatan;
    private String status;
    private String kategori;
    private LocalDate tarikhLahir;
    private String jantina;

    // Payment Information
    private String hubungan;
    private boolean statusOKU;
    private double harga;

    // Additional fields
    private String kaedahPembayaran;

}
