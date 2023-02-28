package com.softmed.api.model.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.softmed.api.exceptions.ValidacaoConsultaException;
import com.softmed.api.model.consulta.DadosAgendamentoConsulta;
import com.softmed.api.repository.ConsultaRepository;

@Component
public class ValidarMedicoComOutraConsulta implements ValidadorConsultas{
	
	@Autowired
	private ConsultaRepository consultaRepository; 
	
	public void validar(DadosAgendamentoConsulta agenda) {
		
		var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository
				                                     .existsByMedicoIdAndDtConsulta(agenda.idMedico(), agenda.data());
		
		if(medicoPossuiOutraConsultaNoMesmoHorario) {
			throw new ValidacaoConsultaException("Médico já possui uma consulta agendada nesse horário");
		}
	}

}
