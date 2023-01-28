package com.softmed.api.pacientes;

import com.softmed.api.endereco.DadosEndereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarDadosPaciente(
		@NotBlank
		String nome,
		@NotBlank
		String telefone,
		@NotNull
		DadosEndereco endereco
){

}
