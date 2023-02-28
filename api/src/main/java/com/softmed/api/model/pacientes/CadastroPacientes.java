package com.softmed.api.model.pacientes;

import org.hibernate.validator.constraints.br.CPF;

import com.softmed.api.model.enderecos.Endereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroPacientes(
		
		@NotBlank
		String nome,
		
		@CPF
		@NotBlank
		String cpf,
		
		@NotBlank
		String telefone,
		
		@Email
		@NotBlank
		String email,
		
		@NotNull
		@Valid
		Endereco endereco		
		
		) {

}
