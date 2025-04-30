package com.ppk.topEntity;

public class APITokenResponse {
private String token;
private String token_type;
private String expires_in;
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
public String getToken_type() {
	return token_type;
}
public void setToken_type(String token_type) {
	this.token_type = token_type;
}
public String getExpires_in() {
	return expires_in;
}
public void setExpires_in(String expires_in) {
	this.expires_in = expires_in;
}


}
