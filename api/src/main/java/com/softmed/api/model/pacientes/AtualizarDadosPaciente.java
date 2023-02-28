package com.softmed.api.model.pacientes;

import com.softmed.api.model.enderecos.DadosEndereco;

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
