package com.ppk.topEntity.formsEntity.registration;

import org.springframework.web.multipart.MultipartFile;

public class UserRequestModel {
    private String emailAhli;
    private String jantina;
    private String branch;
    private String namaAhli;
    private String ic;
    private String passport;
    private String inputDOB;
    private String alamat;
    private String poskod;
    private String negeri;
    private String phone;
    private String race;
    private String finalcate;
    private MultipartFile picImage;
    private MultipartFile paspekerja;
    private MultipartFile kadOKU;
    private MultipartFile paspekerjapasangan;
    private MultipartFile paspekerjaparent;

    private String emailParent;
    private String namaPenjaga;

    public String getEmailAhli() {
        return emailAhli;
    }

    public void setEmailAhli(String emailAhli) {
        this.emailAhli = emailAhli;
    }

    public String getJantina() {
        return jantina;
    }

    public void setJantina(String jantina) {
        this.jantina = jantina;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getNamaAhli() {
        return namaAhli;
    }

    public void setNamaAhli(String namaAhli) {
        this.namaAhli = namaAhli;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getInputDOB() {
        return inputDOB;
    }

    public void setInputDOB(String inputDOB) {
        this.inputDOB = inputDOB;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getFinalcate() {
        return finalcate;
    }

    public void setFinalcate(String finalcate) {
        this.finalcate = finalcate;
    }

    public MultipartFile getPicImage() {
        return picImage;
    }

    public void setPicImage(MultipartFile picImage) {
        this.picImage = picImage;
    }

    public MultipartFile getPaspekerja() {
        return paspekerja;
    }

    public void setPaspekerja(MultipartFile paspekerja) {
        this.paspekerja = paspekerja;
    }

    public MultipartFile getKadOKU() {
        return kadOKU;
    }

    public void setKadOKU(MultipartFile kadOKU) {
        this.kadOKU = kadOKU;
    }

    public MultipartFile getPaspekerjapasangan() {
        return paspekerjapasangan;
    }

    public void setPaspekerjapasangan(MultipartFile paspekerjapasangan) {
        this.paspekerjapasangan = paspekerjapasangan;
    }

    public MultipartFile getPaspekerjaparent() {
        return paspekerjaparent;
    }

    public void setPaspekerjaparent(MultipartFile paspekerjaparent) {
        this.paspekerjaparent = paspekerjaparent;
    }

    public String getEmailParent() {
        return emailParent;
    }

    public void setEmailParent(String emailParent) {
        this.emailParent = emailParent;
    }

    public String getNamaPenjaga() {
        return namaPenjaga;
    }

    public void setNamaPenjaga(String namaPenjaga) {
        this.namaPenjaga = namaPenjaga;
    }

}
