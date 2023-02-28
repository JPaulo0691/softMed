package com.softmed.api.model.consulta;

import com.softmed.api.model.medico.DadosCadastroMedico;
import com.softmed.api.model.pacientes.CadastroPacientes;

public record RealizarConsultaDTO(
		
		DadosCadastroMedico medico,
		CadastroPacientes paciente
				
) {
	
	
}
