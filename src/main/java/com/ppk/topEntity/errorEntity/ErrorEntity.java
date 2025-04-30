package com.ppk.topEntity.errorEntity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorEntity {
	
	private Integer errorCode;
	private String exceptionMessage;
	private String exceptionDescription;
	private LocalDate exceptionDate;
	
	public ErrorEntity(Integer errorCode, String exceptionMessage, String exceptionDescription, LocalDate exceptionDate) {
		this.errorCode=errorCode;
		this.exceptionMessage=exceptionMessage;
		this.exceptionDescription=exceptionDescription;
		this.exceptionDate=exceptionDate;
	}


	
}
