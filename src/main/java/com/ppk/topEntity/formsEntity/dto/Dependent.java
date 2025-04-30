package com.ppk.topEntity.formsEntity.dto;

import java.math.BigDecimal;

public class Dependent {
    private String loginId;
    private String idPengguna;
    private String nokPTanggungan;
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
	private String hubungan;
    private String statusOKU;
    private BigDecimal harga;
}
