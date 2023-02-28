package com.softmed.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softmed.api.model.consulta.DadosAgendamentoConsulta;
import com.softmed.api.model.consulta.DadosDetalhamentoConsulta;
import com.softmed.api.service.ConsultaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/softmed/consulta")
public class ConsultaController {
	
	@Autowired
	private ConsultaService agenda;
	
	@PostMapping
	public ResponseEntity<DadosDetalhamentoConsulta> gerarConsulta(@RequestBody @Valid DadosAgendamentoConsulta consulta) throws IllegalArgumentException {
		
		var agendar = agenda.agendarConsulta(consulta);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(agendar);
	}

}
