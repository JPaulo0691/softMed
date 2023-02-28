package com.softmed.api.model.consulta.validacoes;

import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softmed.api.exceptions.ValidacaoConsultaException;
import com.softmed.api.model.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorConsultas{
	
	public void validar(DadosAgendamentoConsulta agenda) {
		
		var dataConsulta = agenda.data();
		
		var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var antesDaAbertura = dataConsulta.getHour() < 7;
		var encerramentoHorario = dataConsulta.getHour() > 18;
		
		if(domingo || antesDaAbertura || encerramentoHorario) {
			throw new ValidacaoConsultaException("Consulta fora do Horário de funcionamento.Por favor, tente mais tarde.");
		}
	}

}
