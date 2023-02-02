package com.softmed.api.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softmed.api.consulta.ConsultaMedica;
import com.softmed.api.consulta.RealizarConsultaDTO;
import com.softmed.api.medico.Medico;
import com.softmed.api.pacientes.Pacientes;
import com.softmed.api.repository.ConsultaRepository;
import com.softmed.api.repository.MedicoRepository;
import com.softmed.api.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ConsultaService {
	
	@Autowired
	MedicoRepository medicoRepository;
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Autowired
	ConsultaRepository consultaRepository;
	
	@Transactional
    public ConsultaMedica criarConsulta(RealizarConsultaDTO consulta) throws IllegalArgumentException {
		
		System.out.println("AQUIIIIIIIIIIIIIII");
		System.out.println(consulta.medico());
		
		Optional<Medico> medico = medicoRepository.findByCrm(consulta.medico().crm());
		
		Optional<Pacientes> paciente = pacienteRepository.findByCpf(consulta.paciente().cpf());
		
		var consultaMedica = new ConsultaMedica();
		
		if(medico.isEmpty() || paciente.isEmpty()) {
			throw new NoSuchElementException();
		}
		else {	
					
			if(  LocalDate.now().getDayOfWeek().getValue() >= 1 //Segunda
			   & LocalDate.now().getDayOfWeek().getValue() <= 6 // Sábado
			   & LocalTime.now().getHour() <= 19
			   & LocalTime.now().getHour() >= 7) {
				
					consultaMedica.setMedico(medico.get());
					consultaMedica.setPaciente(paciente.get());
			    	
			    	return consultaRepository.save(consultaMedica);
					
			}
			else {
				throw new IllegalArgumentException("Não é possível cadastrar uma consulta. Hora da consulta das 7 as 19.");
			}
			
		}
		
	}
	
}

//Problemas com Serializer
//if(     consultaMedica.getDtConsulta().getDayOfWeek().getValue() >= 1 
//	   & consultaMedica.getDtConsulta().getDayOfWeek().getValue() >= 6 
//	   & consultaMedica.getHoraConsulta().getHour() <= 23
//	   & consultaMedica.getHoraConsulta().getHour() >= 7) {
