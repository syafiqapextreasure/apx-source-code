package com.ppk.topEntity.formsEntity.registration;

public class MemebershipRequestModel {

	private String pId;
	private String status;
	private String bookIssue;
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBookIssue() {
		return bookIssue;
	}
	public void setBookIssue(String bookIssue) {
		this.bookIssue = bookIssue;
	}
	@Override
	public String toString() {
		return "MemebershipRequestModel [pId=" + pId + ", status=" + status + ", bookIssue=" + bookIssue + "]";
	}
	
	
}
