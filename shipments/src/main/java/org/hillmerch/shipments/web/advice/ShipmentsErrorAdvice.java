package org.hillmerch.shipments.web.advice;

import javax.servlet.http.HttpServletRequest;

import org.hillmerch.shipments.client.dto.ErrorMessageDTO;
import org.hillmerch.shipments.web.exception.ShipmentOrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class ShipmentsErrorAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ShipmentOrderNotFoundException.class)
	public ResponseEntity<ErrorMessageDTO> handleCityNotFoundException(ShipmentOrderNotFoundException ex, HttpServletRequest httpServletRequest) {

		return ResponseEntity.status( HttpStatus.NOT_FOUND )
				.contentType( MediaType.APPLICATION_PROBLEM_JSON )
				.body( new ErrorMessageDTO(httpServletRequest.getRequestURI(), HttpStatus.NOT_FOUND.toString(), ex.getMessage())); //como builder
	}

}
