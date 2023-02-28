package com.softmed.api.model.enderecos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
		
		@NotBlank
		String logradouro,
		@NotBlank
		String bairro,
		@Pattern(regexp = "\\d{8}")
		String cep,
		@NotBlank
		String cidade,
		@NotBlank
		String uf,
		String complemento,
		@NotBlank
		String numero) {

}
