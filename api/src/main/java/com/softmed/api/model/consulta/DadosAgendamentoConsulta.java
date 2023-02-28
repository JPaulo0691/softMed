package com.softmed.api.model.consulta;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softmed.api.enums.Especialidade;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;



public record DadosAgendamentoConsulta(
	
	Long idMedico,
	
	@NotNull
	Long idPaciente,
	
	@NotNull
	@Future // tem que ser uma data no Futuro, não pode colocar uma data menor que sysdate

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	LocalDateTime data,
	
	Especialidade especialidade
) {

}
