package com.ppk.utilities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String username;
    private String name;
    private String email;
    
    @JsonProperty("fk_lkp_state")
    private int stateId;
    
    
    @JsonProperty("BANDAR")
    private String BANDAR;
    
    @JsonProperty("NEGERI")
    private String state;
    
    @JsonProperty("fk_lkp_country")
    private int countryId;
    
    @JsonProperty("reference_id")
    private String referenceId;
    
    @JsonProperty("staff_id")
    private String staffId;
    
    //@JsonProperty("birth_date")
    private LocalDate DOB;
    
    @JsonProperty("birth_date")
    private String DOBBirth;
    
    @JsonProperty("place_of_birth")
    private String placeOfBirth;
    
    private String nationality;
    private String gender;
    private String address1;
    private String address2;
    private String address3;
    private String postcode;
    private String city;
    
    @JsonProperty("mobile_no")
    private String mobileNo;
    
    @JsonProperty("home_no")
    private String homeNo;
    
    @JsonProperty("office_no")
    private String officeNo;
    
    @JsonProperty("fax_no")
    private String faxNo;
    
    private List<User> spouse;
    private List<User> child;

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getStateId() { return stateId; }
    public void setStateId(int stateId) { this.stateId = stateId; }

    public int getCountryId() { return countryId; }
    public void setCountryId(int countryId) { this.countryId = countryId; }

    public String getReferenceId() { return referenceId; }
    public void setReferenceId(String referenceId) { this.referenceId = referenceId; }

    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }

     
    
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public LocalDate getDOB() {
		return DOB;
	}
	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}
	/*
	 * public String getPlaceOfBirth() { return placeOfBirth; } public void
	 * setPlaceOfBirth(String placeOfBirth) { this.placeOfBirth = placeOfBirth; }
	 */
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress1() { return address1; }
    public void setAddress1(String address1) { this.address1 = address1; }

    public String getAddress2() { return address2; }
    public void setAddress2(String address2) { this.address2 = address2; }

    public String getAddress3() { return address3; }
    public void setAddress3(String address3) { this.address3 = address3; }

    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getHomeNo() { return homeNo; }
    public void setHomeNo(String homeNo) { this.homeNo = homeNo; }

    public String getOfficeNo() { return officeNo; }
    public void setOfficeNo(String officeNo) { this.officeNo = officeNo; }

    public String getFaxNo() { return faxNo; }
    public void setFaxNo(String faxNo) { this.faxNo = faxNo; }

    public List<User> getSpouse() { return spouse; }
    public void setSpouse(List<User> spouse) { this.spouse = spouse; }

    public List<User> getChild() { return child; }
    public void setChild(List<User> child) { this.child = child; }
	public String getDOBBirth() {
		return DOBBirth;
	}
	public void setDOBBirth(String dOBBirth) {
		DOBBirth = dOBBirth;
	}
	public String getBANDAR() {
		return BANDAR;
	}
	public void setBANDAR(String bANDAR) {
		BANDAR = bANDAR;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
}
