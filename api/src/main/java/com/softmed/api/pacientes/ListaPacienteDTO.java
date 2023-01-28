package com.softmed.api.pacientes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListaPacienteDTO {
	
	private String nome;
	private String email;
	private String cpf;
	
	public ListaPacienteDTO(Pacientes paciente) {
		
		this.nome = paciente.getNome();
		this.email = paciente.getEmail();
		this.cpf = paciente.getCpf();
	}

}
