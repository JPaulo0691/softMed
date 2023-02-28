package com.softmed.api.model.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softmed.api.exceptions.ValidacaoConsultaException;
import com.softmed.api.model.consulta.DadosAgendamentoConsulta;
import com.softmed.api.repository.MedicoRepository;

@Component
public class ValidarMedicoAtivo implements ValidadorConsultas{
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void validar(DadosAgendamentoConsulta agenda) {
		
		if(agenda.idMedico() == null) {
			return;
		}
		
//		var medicoEstaAtivo = medicoRepository.findAtivoById(agenda.idMedico());
//		
//		if(!medicoEstaAtivo) {
//			throw new ValidacaoConsultaException("A consulta não pode ser agendada pois o médico não possui cadastro ativo");
//		}
	}

}
