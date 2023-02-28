package com.softmed.api.model.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.softmed.api.exceptions.ValidacaoConsultaException;
import com.softmed.api.model.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoHorarioAntecedencia implements ValidadorConsultas {
	
	
	public void validar(DadosAgendamentoConsulta agenda) {
		
		var dataConsulta = agenda.data();
		var agora = LocalDateTime.now();
		
		var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
		
		if(diferencaEmMinutos < 30) {
			throw new ValidacaoConsultaException("Consulta deve ser agendada com 30 minutos de antecedência, no mínimo.");
		}
	}

}
