package com.softmed.api.model.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.softmed.api.exceptions.ValidacaoConsultaException;
import com.softmed.api.model.consulta.DadosAgendamentoConsulta;
import com.softmed.api.repository.PacienteRepository;

@Component
public class ValidarPacienteAtivo implements ValidadorConsultas{
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public void validar(DadosAgendamentoConsulta agenda) {
		
		var pacienteAtivo = pacienteRepository.findAtivoById(agenda.idPaciente());
		
		if(!pacienteAtivo) {
			throw new ValidacaoConsultaException("Consulta não pode ser agendada para paciente com cadastro inativo");
		}
	}

}
