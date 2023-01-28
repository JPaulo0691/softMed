package com.softmed.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softmed.api.consulta.ConsultaMedica;
import com.softmed.api.repository.ConsultaRepository;

@Service
public class ConsultaService {
	
	@Autowired
	ConsultaRepository consultaRepository;
	
	public ConsultaMedica criarConsulta(ConsultaMedica consulta) {
			
		return consultaRepository.save(consulta);
	}
}
