package com.ppk.topEntity.formsEntity.dto;

public class AdminBookList {

	   private String ct03DocNo;
	    private String ct03Loca;
	    private String ct03Icat;

	    // Constructors, Getters, and Setters
	    public AdminBookList() {}
	    public AdminBookList(String ct03DocNo, String ct03Loca, String ct03Icat) {
	        this.ct03DocNo = ct03DocNo;
	        this.ct03Loca = ct03Loca;
	        this.ct03Icat = ct03Icat;
	    }

	    // Getters and Setters
	    public String getCt03DocNo() {
	        return ct03DocNo;
	    }

	    public void setCt03DocNo(String ct03DocNo) {
	        this.ct03DocNo = ct03DocNo;
	    }

	    public String getCt03Loca() {
	        return ct03Loca;
	    }

	    public void setCt03Loca(String ct03Loca) {
	        this.ct03Loca = ct03Loca;
	    }

	    public String getCt03Icat() {
	        return ct03Icat;
	    }

	    public void setCt03Icat(String ct03Icat) {
	        this.ct03Icat = ct03Icat;
	    }

	    @Override
	    public String toString() {
	        return "Document{" +
	                "ct03DocNo='" + ct03DocNo + '\'' +
	                ", ct03Loca='" + ct03Loca + '\'' +
	                ", ct03Icat='" + ct03Icat + '\'' +
	                '}';
	    }
	}