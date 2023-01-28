package com.softmed.api.medico;

import com.softmed.api.endereco.DadosEndereco;
import com.softmed.api.endereco.Endereco;

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
