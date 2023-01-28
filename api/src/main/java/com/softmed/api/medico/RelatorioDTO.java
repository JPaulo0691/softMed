package com.softmed.api.medico;

import com.softmed.api.enums.Especialidade;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RelatorioDTO {
	
	private String nome;
	private String crm;
	private String email;
	private String telefone;
	private Especialidade especialidade;
	private String logradouro;
	
	public RelatorioDTO(Medico medico) {
		this.nome = medico.getNome();
		this.email = medico.getEmail();
		this.telefone = medico.getTelefone();
		this.especialidade = medico.getEspecialidade();
		this.logradouro = medico.getEndereco().getLogradouro();
	}

}
