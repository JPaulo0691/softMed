package com.softmed.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class MensagensDeErro {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity error404() {
		
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity error400(MethodArgumentNotValidException ex) {
		
		var errors = ex.getFieldErrors();
		
		return ResponseEntity.badRequest()
				             .body(errors.stream()
				             .map(DadosErroValidacao::new).toList());
	}
	
	private record DadosErroValidacao(String campo, String mensagem) {
		
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
		
	}
}
