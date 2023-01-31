package com.softmed.api.consulta;

import com.softmed.api.medico.DadosCadastroMedico;
import com.softmed.api.pacientes.CadastroPacientes;

public record RealizarConsultaDTO(
		
		DadosCadastroMedico medico,
		CadastroPacientes paciente
		
		
) {
	
	
}
