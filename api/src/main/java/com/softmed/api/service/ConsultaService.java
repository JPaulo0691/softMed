package com.softmed.api.service;

import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ConsultaMedica criarConsulta(RealizarConsultaDTO consulta) throws IllegalAccessException {
		
		
		Optional<Medico> medico = medicoRepository.findByCrm(consulta.medico().crm());
		
		Optional<Pacientes> paciente = pacienteRepository.findByCpf(consulta.paciente().cpf());
		
		var consultaMedica = new ConsultaMedica();
		
		if(medico.isEmpty() || paciente.isEmpty()) {
			throw new NoSuchElementException();
		}
		else {		
			
			if(consultaMedica.getMedico().getAtivo() != true || consultaMedica.getPaciente().getAtivo() != true) {
				throw new IllegalAccessException("Não é permitido realizar uma consulta, médico ou paciente inativo");
			}
			else {
				consultaMedica.setMedico(medico.get());
				consultaMedica.setPaciente(paciente.get());
		    	
		    	return consultaRepository.save(consultaMedica);
			}
			
		}		
		
	}
	
	public void horarioFuncionamento(ConsultaMedica data, ConsultaMedica hora) {
		
		
	}
}
