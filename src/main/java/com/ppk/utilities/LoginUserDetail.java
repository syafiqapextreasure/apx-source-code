package com.ppk.utilities;

import java.time.LocalDate;

public class LoginUserDetail {
private String firstName;
private String lastName;
private LocalDate DOB;
private String emailAddress;
private String screenName;
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
 
public LocalDate getDOB() {
	return DOB;
}
public void setDOB(LocalDate dOB) {
	DOB = dOB;
}
public String getEmailAddress() {
	return emailAddress;
}
public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}
public String getScreenName() {
	return screenName;
}
public void setScreenName(String screenName) {
	this.screenName = screenName;
}


}
