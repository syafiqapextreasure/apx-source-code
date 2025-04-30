package com.ppk.topController.membership.renual.entity;

import java.math.BigDecimal;

public class RenewalMembershipData {
	@Override
	public String toString() {
		return "RenewalMembershipData [patronID=" + patronID + ", name=" + name + ", address=" + address
				+ ", dateEnrolled=" + dateEnrolled + ", expiryDate=" + expiryDate + ", contactNo=" + contactNo
				+ ", email=" + email + ", itemsChargedTD=" + itemsChargedTD + ", lastChargeDate=" + lastChargeDate
				+ ", lateReturnsTD=" + lateReturnsTD + ", lastReturnDate=" + lastReturnDate + ", lostItems=" + lostItems
				+ ", lastRenewDate=" + lastRenewDate + ", suspension=" + suspension + ", outstanding=" + outstanding
				+ ", itemsChargedYTD=" + itemsChargedYTD + ", finesPaid=" + finesPaid + ", lateReturnsYTD="
				+ lateReturnsYTD + ", groupID=" + groupID + ", department=" + department + ", codeStatus=" + codeStatus
				+ ", status=" + status + ", category=" + category + ", dob=" + dob + ", gender=" + gender + "]";
	}

	private String patronID;
	private String name;
	private String address;
	private String dateEnrolled;
	private String expiryDate;
	private String contactNo;
	private String email;
	private int itemsChargedTD;
	private String lastChargeDate;
	private int lateReturnsTD;
	private String lastReturnDate;
	private int lostItems;
	private String lastRenewDate;
	private String suspension;
	private BigDecimal outstanding;
	private int itemsChargedYTD;
	private String finesPaid;
	private int lateReturnsYTD;
	private String groupID;
	private String department;
	private String codeStatus;
	private String status;
	private String category;
	private String dob;
	private String gender;

	public RenewalMembershipData(String patronID, String name, String address, String dateEnrolled, String expiryDate,
			String contactNo, String email, int itemsChargedTD, String lastChargeDate, int lateReturnsTD,
			String lastReturnDate, int lostItems, String lastRenewDate, String suspension, int itemsChargedYTD,
			int lateReturnsYTD, String groupID, String department, String codeStatus, String status, String category,
			String dob, String gender) {
		this.patronID = patronID;
		this.name = name;
		this.address = address;
		this.dateEnrolled = dateEnrolled;
		this.expiryDate = expiryDate;
		this.contactNo = contactNo;
		this.email = email;
		this.itemsChargedTD = itemsChargedTD;
		this.lastChargeDate = lastChargeDate;
		this.lateReturnsTD = lateReturnsTD;
		this.lastReturnDate = lastReturnDate;
		this.lostItems = lostItems;
		this.lastRenewDate = lastRenewDate;
		this.suspension = suspension;
		this.itemsChargedYTD = itemsChargedYTD;
		this.lateReturnsYTD = lateReturnsYTD;
		this.groupID = groupID;
		this.department = department;
		this.codeStatus = codeStatus;
		this.status = status;
		this.category = category;
		this.dob = dob;
		this.gender = gender;
	}

	public RenewalMembershipData() {
		// TODO Auto-generated constructor stub
	}

	public String getPatronID() {
		return patronID;
	}

	public void setPatronID(String patronID) {
		this.patronID = patronID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateEnrolled() {
		return dateEnrolled;
	}

	public void setDateEnrolled(String dateEnrolled) {
		this.dateEnrolled = dateEnrolled;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getItemsChargedTD() {
		return itemsChargedTD;
	}

	public void setItemsChargedTD(int itemsChargedTD) {
		this.itemsChargedTD = itemsChargedTD;
	}

	public String getLastChargeDate() {
		return lastChargeDate;
	}

	public void setLastChargeDate(String lastChargeDate) {
		this.lastChargeDate = lastChargeDate;
	}

	public int getLateReturnsTD() {
		return lateReturnsTD;
	}

	public void setLateReturnsTD(int lateReturnsTD) {
		this.lateReturnsTD = lateReturnsTD;
	}

	public String getLastReturnDate() {
		return lastReturnDate;
	}

	public void setLastReturnDate(String lastReturnDate) {
		this.lastReturnDate = lastReturnDate;
	}

	public int getLostItems() {
		return lostItems;
	}

	public void setLostItems(int lostItems) {
		this.lostItems = lostItems;
	}

	public String getLastRenewDate() {
		return lastRenewDate;
	}

	public void setLastRenewDate(String lastRenewDate) {
		this.lastRenewDate = lastRenewDate;
	}

	public String getSuspension() {
		return suspension;
	}

	public void setSuspension(String suspension) {
		this.suspension = suspension;
	}

	public BigDecimal getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(BigDecimal outstanding) {
		this.outstanding = outstanding;
	}

	public int getItemsChargedYTD() {
		return itemsChargedYTD;
	}

	public void setItemsChargedYTD(int itemsChargedYTD) {
		this.itemsChargedYTD = itemsChargedYTD;
	}

	public String getFinesPaid() {
		return finesPaid;
	}

	public void setFinesPaid(String finesPaid) {
		this.finesPaid = finesPaid;
	}

	public int getLateReturnsYTD() {
		return lateReturnsYTD;
	}

	public void setLateReturnsYTD(int lateReturnsYTD) {
		this.lateReturnsYTD = lateReturnsYTD;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCodeStatus() {
		return codeStatus;
	}

	public void setCodeStatus(String codeStatus) {
		this.codeStatus = codeStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
