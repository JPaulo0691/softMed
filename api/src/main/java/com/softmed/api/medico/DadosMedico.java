package com.softmed.api.medico;

import com.softmed.api.enums.Especialidade;

public record DadosMedico(String nome,String email,String crm,	Especialidade especialidade, Boolean ativo) {
	
	public DadosMedico(Medico medico) {
		this(medico.getNome(), medico.getEmail(),medico.getCrm(), medico.getEspecialidade(), medico.getAtivo() );
	}

}
