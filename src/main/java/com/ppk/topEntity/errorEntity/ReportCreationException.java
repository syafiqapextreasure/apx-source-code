package com.ppk.topEntity.errorEntity;

public class ReportCreationException extends Exception {
	private String customException;
	public ReportCreationException(String customException) {
		this.customException=customException;
	}

}
