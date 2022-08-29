package com.blueline.utility;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import com.blueline.dto.ErrorInfoDTO;
import com.blueline.exception.TaxiServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {
	@Autowired
	private Environment environment;
	
	@ExceptionHandler(TaxiServiceException.class)
	public ResponseEntity<ErrorInfoDTO> exceptionHandler(TaxiServiceException ex) {
		 ErrorInfoDTO error = new ErrorInfoDTO();
	     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	     error.setErrorMessage(environment.getProperty(ex.getMessage()));
	     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Exception handler for general exception "Exception".
	 * 
	 * @param ex
	 * @return the error information with error code and error message
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfoDTO> generalExceptionHandler(Exception ex) {
		log.error(ex.getMessage(), ex);

		ErrorInfoDTO errorInfoDTO = new ErrorInfoDTO();
		errorInfoDTO.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfoDTO.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfoDTO);
	}

	/**
	 * Exception handler for MethodArgumentNotValidException and
	 * ConstraintViolationException.
	 *
	 * @param ex
	 * @return the error information with error code and error message
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
	public ResponseEntity<ErrorInfoDTO> exceptionHandler2(Exception ex) {
		log.error(ex.getMessage(), ex);
		String errorMsg;
		if (ex instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;
			errorMsg = manve.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.joining(", "));
		} else {
			ConstraintViolationException cve = (ConstraintViolationException) ex;

			errorMsg = cve.getConstraintViolations().stream().map(x -> x.getMessage())
					.collect(Collectors.joining(", "));
		}
		ErrorInfoDTO errorInfoDTO = new ErrorInfoDTO();
		errorInfoDTO.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfoDTO.setErrorMessage(errorMsg);
		return new ResponseEntity<>(errorInfoDTO, HttpStatus.BAD_REQUEST);
	}
}
