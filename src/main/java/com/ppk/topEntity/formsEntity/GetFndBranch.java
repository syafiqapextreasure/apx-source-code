package com.ppk.topEntity.formsEntity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

//Lokasi PPK Pilihan 
@Entity
@Table(name = "GLBRNC")
@Data
public class GetFndBranch {
    
    @Id
    @Column(name = "GL71BRNC")
    private String code;
    
    @Column(name = "GL71DESC")
    private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
    
}
