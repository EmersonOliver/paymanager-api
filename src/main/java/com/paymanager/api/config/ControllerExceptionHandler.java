package com.paymanager.api.config;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paymanager.api.dtos.ErroHandler;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErroHandler> threadDuplicateEntry(DataIntegrityViolationException exception) {
		ErroHandler error = new ErroHandler("Usuario ja cadastrado", "400");
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErroHandler> thread404(EntityNotFoundException exception) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroHandler> threadGeneral(Exception exception) {
		ErroHandler error = new ErroHandler("Internal Server Error", "500");
		return ResponseEntity.internalServerError().body(error);
	}
}
