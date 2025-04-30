package com.ppk.topEntity.errorEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserNotFoundException extends Exception {
	private String customException;
	public UserNotFoundException(String customException) {
		this.customException=customException;
	}
	

}
