package com.softmed.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softmed.api.model.pacientes.AtualizarDadosPaciente;
import com.softmed.api.model.pacientes.CadastroPacientes;
import com.softmed.api.model.pacientes.ListaPacienteDTO;
import com.softmed.api.model.pacientes.Pacientes;
import com.softmed.api.service.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	@Autowired
	PacienteService pacienteService;
	
	@PostMapping
	public ResponseEntity<Pacientes> cadastrar(@RequestBody @Valid CadastroPacientes cadastroPacientes) {
		return ResponseEntity.status(HttpStatus.CREATED)
	              .body(pacienteService.cadastrarPaciente(new Pacientes(cadastroPacientes)));
	}
	
	@GetMapping("/listarpacientes")
	public Page<ListaPacienteDTO> listarPaciente(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		return pacienteService.listaPaciente(paginacao);
	}
	
	@PutMapping("/atualizardados/{id}")
	public ResponseEntity<Pacientes> atualizarPaciente(@PathVariable Long id,
			                                           @RequestBody AtualizarDadosPaciente dadosPaciente){
		
		return ResponseEntity.status(HttpStatus.OK)
				              .body(pacienteService.atualizarCadastro(id, new Pacientes(dadosPaciente)));
		
	}
	
	@DeleteMapping("/inativarPaciente/{id}")
	public ResponseEntity<Pacientes> inativarPaciente(@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK)
				             .body(pacienteService.inativarRegistro(id));
				       
	}
	
}
