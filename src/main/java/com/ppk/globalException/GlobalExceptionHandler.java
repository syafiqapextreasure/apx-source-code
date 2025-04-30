package com.ppk.globalException;

import java.time.LocalDate;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import com.ppk.topEntity.errorEntity.ErrorEntity;
import com.ppk.topEntity.errorEntity.ReportCreationException;
import com.ppk.topEntity.errorEntity.ResourceNotFound;
import com.ppk.topEntity.errorEntity.UserNotFoundException;
import com.ppk.utilities.LogUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

	Logger logger = LogUtil.getLogger(GlobalExceptionHandler.class);

	  @ExceptionHandler(Exception.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    public String handleException(Exception ex, org.springframework.ui.Model model) {
	        // Add exception details to the model
	        model.addAttribute("exception", ex.getClass().getSimpleName());
	        model.addAttribute("errorMessage", ex.getMessage());
	        model.addAttribute("currentTime", LocalDateTime.now());
	        return "errorPage";  // This will render the error.html template
	    }
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
//		this.logger.error("Request receive from GlobalExceptionHandler. ");
//		ErrorEntity errorDetails = new ErrorEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
//				request.getDescription(false), LocalDate.now());
//		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<?> resourceNotFoundException(Exception ex, WebRequest request) {
		this.logger.error("Request receive from GlobalExceptionHandler. ");
		ErrorEntity errorDetails = new ErrorEntity(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getDescription(false), LocalDate.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFoundHandler(Exception ex, WebRequest request) {
		this.logger.error("Request receive from UserNotFoundExcepton. ");
		ErrorEntity errorEntity=new ErrorEntity(HttpStatus.NOT_FOUND.value(), ex.getMessage(), 
				request.getDescription(false), LocalDate.now());
		return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(ReportCreationException.class)
	public ResponseEntity<?> ReportCreationHandler(Exception ex, WebRequest request) {
		this.logger.error("Request receive from UserNotFoundExcepton. ");
		ErrorEntity errorEntity=new ErrorEntity(HttpStatus.NOT_FOUND.value(), ex.getMessage(), 
				request.getDescription(false), LocalDate.now());
		return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
	}
}
