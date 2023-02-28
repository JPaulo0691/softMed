package com.softmed.api.model.enderecos;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
	

	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private String complemento;
	private String numero;
	
	public Endereco(DadosEndereco endereco) {
		this.logradouro = endereco.logradouro();
		this.bairro = endereco.bairro();
		this.cep = endereco.cep();
		this.cidade = endereco.cidade();
		this.uf = endereco.uf();
		this.complemento = endereco.complemento();
		this.numero = endereco.numero();
	}

}
