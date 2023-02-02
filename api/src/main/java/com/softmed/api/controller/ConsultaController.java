package com.softmed.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softmed.api.consulta.ConsultaMedica;
import com.softmed.api.consulta.RealizarConsultaDTO;
import com.softmed.api.service.ConsultaService;

@RestController
@RequestMapping("/api/softmed/consulta")
public class ConsultaController {
	
	@Autowired
	private ConsultaService consultaService;
	
	@PostMapping
	public ConsultaMedica gerarConsulta(@RequestBody RealizarConsultaDTO consulta) throws IllegalArgumentException {
		
		return consultaService.criarConsulta(consulta);
	}

}
