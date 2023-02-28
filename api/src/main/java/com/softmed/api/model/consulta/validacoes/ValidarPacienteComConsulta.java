package com.softmed.api.model.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softmed.api.exceptions.ValidacaoConsultaException;
import com.softmed.api.model.consulta.DadosAgendamentoConsulta;
import com.softmed.api.repository.ConsultaRepository;

@Component
public class ValidarPacienteComConsulta implements ValidadorConsultas{
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	public void validar(DadosAgendamentoConsulta agenda) {
		
		var horaInicial = agenda.data().withHour(7);
		var horaFinal = agenda.data().withHour(18);
		
		var pacientePossuiOutraConsultaNoDia = consultaRepository
				                               .existsByPacienteIdAndDtConsultaBetween(agenda.idPaciente(), horaInicial, horaFinal);
		
		if(pacientePossuiOutraConsultaNoDia) {
			throw new ValidacaoConsultaException("Paciente já possui uma consulta marcada para esse dia");
		}
	}

}
