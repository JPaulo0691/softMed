package com.softmed.api.model.consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softmed.api.enums.Especialidade;
import com.softmed.api.model.medico.Medico;
import com.softmed.api.model.pacientes.Pacientes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultaDiariaDetalhamentoDTO {
	
	private String nomeMedico;
	private String crm;
	private Especialidade especialidade;
	private String    nomePaciente;
	private String    telefone;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataConsulta;
	
	public ConsultaDiariaDetalhamentoDTO(ConsultaMedica consulta) {
		
		this.nomeMedico = consulta.getMedico().getNome();
		this.crm = consulta.getMedico().getCrm();
		this.especialidade = consulta.getMedico().getEspecialidade();
		this.nomePaciente = consulta.getPaciente().getNome();
		this.telefone = consulta.getPaciente().getTelefone();
		this.dataConsulta = consulta.getDtConsulta();
	}
	

}
