package com.softmed.api.model.medico;

import com.softmed.api.model.enderecos.DadosEndereco;
import com.softmed.api.model.enderecos.Endereco;

import jakarta.validation.constraints.NotBlank;

public record AtualizarDadosMedico(
		
		@NotBlank
		String nome,
		@NotBlank
		String telefone,
		@NotBlank
	    DadosEndereco endereco
		) {
	
}
