package com.ppk.lost.material.model;

public class CirBahanRosakHilangtblPinjaman {
	
    private String noPerolehan;
    private String judul;
    private String status;
    private String tarikhPinjam;
    private String tarikhPulang;
    private String mapNumber;
    

    // Constructor
    public CirBahanRosakHilangtblPinjaman(String noPerolehan,String judul, String tarikhPinjam, String tarikhPulang,String matNumner) {
        this.noPerolehan = noPerolehan;
        this.judul = judul;
        this.tarikhPinjam = tarikhPinjam;
        this.tarikhPulang = tarikhPulang;
        this.mapNumber=matNumner;
    }

    

	public String getMapNumber() {
		return mapNumber;
	}



	public void setMapNumber(String mapNumber) {
		this.mapNumber = mapNumber;
	}



	// Getters and setters
    public String getNoPerolehan() {
        return noPerolehan;
    }

    public String getJudul() {
        return judul;
    }

    public String getTarikhPinjam() {
        return tarikhPinjam;
    }

    public String getTarikhPulang() {
        return tarikhPulang;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}