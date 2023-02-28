package com.softmed.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softmed.api.model.pacientes.ListaPacienteDTO;
import com.softmed.api.model.pacientes.Pacientes;
import com.softmed.api.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class PacienteService {
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Transactional
	public Pacientes cadastrarPaciente(Pacientes paciente) {
		
		return pacienteRepository.save(paciente);
	}
	
	public Page<ListaPacienteDTO> listaPaciente(Pageable paginacao){
		return pacienteRepository.findAll(paginacao).map(ListaPacienteDTO::new);
	}
	
	@Transactional
	public Pacientes atualizarCadastro(Long id, Pacientes paciente) {
		
		Optional<Pacientes> idPaciente = pacienteRepository.findById(id);
		
		if(idPaciente.isEmpty()) {
			throw new IllegalArgumentException("Dados não Encontrados");
		}
		else {
			var pacientes = idPaciente.get();
			
			pacientes.setNome(paciente.getNome());
			pacientes.setTelefone(paciente.getTelefone());
			pacientes.setEndereco(paciente.getEndereco());
			
			return pacienteRepository.save(pacientes);
		}
		
	}
	
	@Transactional
	public Pacientes inativarRegistro(Long id) {
		
		Optional<Pacientes> buscarId = pacienteRepository.findById(id);
		
		if(buscarId.isEmpty()) {
			throw new IllegalArgumentException("Dados não Encontrados!");
		}
		else {
			var paciente = buscarId.get();
			
			paciente.setAtivo(false);
			
			return pacienteRepository.save(paciente);
		}
	}

}
