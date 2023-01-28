package com.softmed.api.consulta;

import com.softmed.api.enums.Especialidade;

public record RealizarConsultaDTO(
		
		String  nomeMedico,
		String  crm,
		Boolean medicoAtivo,
		Especialidade Especialidade,
		String nomePaciente,
		String cpf,
		String email,
		Boolean pacienteAtivo
		
) {
	
	public RealizarConsultaDTO(ConsultaMedica consulta){
		this(consulta.getMedico().getNome(),
			 consulta.getMedico().getCrm(),
			 consulta.getMedico().getAtivo(),
			 consulta.getMedico().getEspecialidade(),
			 consulta.getPaciente().getNome(),
			 consulta.getPaciente().getCpf(),
			 consulta.getPaciente().getEmail(),
			 consulta.getPaciente().getAtivo()
		    
		);
		
	}

}
