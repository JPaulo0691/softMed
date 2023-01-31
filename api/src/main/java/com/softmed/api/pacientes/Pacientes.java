package com.softmed.api.pacientes;

import org.hibernate.validator.constraints.br.CPF;

import com.softmed.api.consulta.ConsultaMedica;
import com.softmed.api.endereco.Endereco;
import com.softmed.api.enums.Especialidade;
import com.softmed.api.medico.Medico;

import jakarta.annotation.Generated;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "PACIENTES")
public class Pacientes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	
	@NotBlank
	private String nome;
	
	@CPF
	private String cpf;
	@NotBlank
	private String telefone;
	
	@Email
	private String email;
	
	private Boolean ativo;
	
	@Embedded
	private Endereco endereco;
	
	public Pacientes(CadastroPacientes cadastrar){
		
		this.nome = cadastrar.nome();
		this.cpf = cadastrar.cpf();
		this.email = cadastrar.email();
		this.telefone = cadastrar.telefone();
		this.endereco = cadastrar.endereco();
		this.ativo = true;
	}
	
	public Pacientes(AtualizarDadosPaciente dadosPaciente) {
		
		if(dadosPaciente != null) {
			this.nome = dadosPaciente.nome();
			this.telefone = dadosPaciente.telefone();
			this.endereco = new Endereco(dadosPaciente.endereco());
		}
		
	}

}
