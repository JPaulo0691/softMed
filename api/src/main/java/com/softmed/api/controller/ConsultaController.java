package com.softmed.api.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softmed.api.model.consulta.ConsultaDiariaDetalhamentoDTO;
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
	
	@GetMapping("/relatoriosDeConsulta")
	public ResponseEntity<Page<ConsultaDiariaDetalhamentoDTO>> relatorioConsulta(@PageableDefault(size = 5 )Pageable paginacao
			                                                  ,@RequestParam("dataInicial")
	                                                           @DateTimeFormat(iso = ISO.DATE_TIME)LocalDateTime dataInicial
	                                                          ,@RequestParam("dataFinal")
	                                                          @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataFinal){
		
		var relatorio = agenda.relatorioDeConsultas(paginacao,dataInicial,dataFinal);
		
		return ResponseEntity.status(HttpStatus.OK).body(relatorio);
	}

}
