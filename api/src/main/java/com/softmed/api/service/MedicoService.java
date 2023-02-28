package com.softmed.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softmed.api.model.medico.DadosMedico;
import com.softmed.api.model.medico.Medico;
import com.softmed.api.model.medico.RelatorioDTO;
import com.softmed.api.repository.MedicoRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Transactional
	public Medico cadastrar(Medico medico) {
		
		return medicoRepository.save(medico);
	}
	
	public List<Medico> findAllDoctors(){
		return medicoRepository.findAll();
	}
	
	public Page<DadosMedico> findInfoDoctors(Pageable paginacao){
		return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosMedico::new);
	}
	
	public List<RelatorioDTO> getRelatorio(){
		return medicoRepository.findAll().stream().map(RelatorioDTO::new).toList();
	}
	
	@Transactional
	public Medico atualizar(String crm, Medico medico) {
		
		Optional<Medico> crmOptional = medicoRepository.findByCrm(crm);
		
		if(crmOptional.isEmpty()) {
			throw new IllegalArgumentException("Dados inválidos");
		}
		else {
			var updateMedico = crmOptional.get();
			
			updateMedico.setNome(medico.getNome());
			updateMedico.setTelefone(medico.getTelefone());
			updateMedico.setEndereco(medico.getEndereco());
			
			return medicoRepository.save(updateMedico);
		}	
	}
	
	@Transactional
	public Medico deletarMedico(String crm) {
		Optional<Medico> excluirMedico = medicoRepository.findByCrm(crm);
		
		if(excluirMedico.isEmpty()) {
			throw new IllegalArgumentException("CRM inválido ou não encontrado.");
		}
		else {
			var medico = excluirMedico.get();
			medico.setAtivo(false);
			
			return medicoRepository.save(medico);
		}	
	}
}
