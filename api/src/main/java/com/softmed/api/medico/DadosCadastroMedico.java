package com.softmed.api.medico;

import com.softmed.api.endereco.DadosEndereco;
import com.softmed.api.enums.Especialidade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
		
		@NotBlank
		String nome,
		
		@Email
		@NotBlank
		String email,
		
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm,
		
		@NotNull
		String telefone,
		
		@NotNull
		Especialidade especialidade,
		
		@NotNull
		@Valid
		DadosEndereco endereco) {
	
}
