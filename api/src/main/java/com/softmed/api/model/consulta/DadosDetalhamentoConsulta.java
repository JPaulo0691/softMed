package com.softmed.api.model.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
		
		Long id,
		Long idMedico,
		Long idPaciente,
		LocalDateTime data
		
) {

	public DadosDetalhamentoConsulta(ConsultaMedica consulta) {
		this(consulta.getIdConsulta(), consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getDtConsulta());
	}

}
