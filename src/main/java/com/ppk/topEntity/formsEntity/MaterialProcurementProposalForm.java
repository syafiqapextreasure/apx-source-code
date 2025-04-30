package com.ppk.topEntity.formsEntity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "efciusrr")
public class MaterialProcurementProposalForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "email")
    private String email;

    @Column(name = "judul")
    private String title;

    @Column(name = "subjek")
    private String subjek;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "pengarang")
    private String author;

    @Column(name = "penerbit")
    private String penerbit;

    @Column(name = "edisi")
    private String edisi;

    @Column(name = "bil_salinan")
    private Integer bilSalinan;

    @Column(name = "jenis_bahan")
    private String jenisBahan;

    @Column(name = "bil_set")
    private Integer bilSet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubjek() {
		return subjek;
	}

	public void setSubjek(String subjek) {
		this.subjek = subjek;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPenerbit() {
		return penerbit;
	}

	public void setPenerbit(String penerbit) {
		this.penerbit = penerbit;
	}

	public String getEdisi() {
		return edisi;
	}

	public void setEdisi(String edisi) {
		this.edisi = edisi;
	}

	public Integer getBilSalinan() {
		return bilSalinan;
	}

	public void setBilSalinan(Integer bilSalinan) {
		this.bilSalinan = bilSalinan;
	}

	public String getJenisBahan() {
		return jenisBahan;
	}

	public void setJenisBahan(String jenisBahan) {
		this.jenisBahan = jenisBahan;
	}

	public Integer getBilSet() {
		return bilSet;
	}

	public void setBilSet(Integer bilSet) {
		this.bilSet = bilSet;
	}
    
    
    
}
