package com.softmed.api.medico;

import java.util.ArrayList;
import java.util.List;

import com.softmed.api.consulta.ConsultaMedica;
import com.softmed.api.endereco.Endereco;
import com.softmed.api.enums.Especialidade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "MEDICOS")
public class Medico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String crm;
	private String telefone;
	private Boolean ativo;
	
	
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;
	
	@OneToMany(mappedBy = "medico",  fetch = FetchType.LAZY)
	private List<ConsultaMedica> listaConsulta;	
	
    public Medico(DadosCadastroMedico dadosCadastro) {
    	this.ativo = true;
    	this.nome = dadosCadastro.nome();
    	this.email = dadosCadastro.email();
    	this.crm = dadosCadastro.crm();
    	this.telefone = dadosCadastro.telefone();
    	this.especialidade = dadosCadastro.especialidade();
    	this.endereco = new Endereco(dadosCadastro.endereco());
	}

	public Medico(AtualizarDadosMedico atualizarDados) {
		if(atualizarDados != null) {
			this.nome = atualizarDados.nome();
			this.telefone = atualizarDados.telefone();
			this.endereco = new Endereco(atualizarDados.endereco());
		}
		
	}
	
}
