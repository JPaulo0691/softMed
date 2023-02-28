package com.softmed.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softmed.api.model.medico.AtualizarDadosMedico;
import com.softmed.api.model.medico.DadosCadastroMedico;
import com.softmed.api.model.medico.DadosMedico;
import com.softmed.api.model.medico.Medico;
import com.softmed.api.model.medico.RelatorioDTO;
import com.softmed.api.service.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/softmed")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@PostMapping 
	public ResponseEntity<Medico> cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dadosCadastro ) {
		 
		 
		 return ResponseEntity.status(HttpStatus.OK)
				              .body(medicoService.cadastrar(new Medico(dadosCadastro))); // aqui recebe informações
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Medico>> allDoctors(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(medicoService.findAllDoctors());
		
	}
	
	@GetMapping("/guiaMedico")
	public ResponseEntity<Page<DadosMedico>> listaMedica(@PageableDefault(size = 5, sort = {"nome"})  Pageable paginacao){
		return ResponseEntity.status(HttpStatus.OK)
				.body(medicoService.findInfoDoctors(paginacao));
	}
	
	@GetMapping("/relatorio")
	public List<RelatorioDTO> relatorioMedico(){
		return medicoService.getRelatorio();
	}
	
	@PutMapping("/atualizarRegistro/{crm}")
	public ResponseEntity<Medico> atualizar(@PathVariable(value = "crm") String crm,
			                @RequestBody AtualizarDadosMedico atualizarDados) {
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(medicoService.atualizar(crm, new Medico(atualizarDados)));
	}
	
	@DeleteMapping("/excluirMedico/{crm}")
	public ResponseEntity<Medico> deletar(@PathVariable String crm) {
		
		if(crm.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(medicoService.deletarMedico(crm));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(medicoService.deletarMedico(crm));
		}	
		
	}
	

}
