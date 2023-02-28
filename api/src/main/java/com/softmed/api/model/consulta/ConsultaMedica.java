package com.softmed.api.model.consulta;

import java.time.LocalDateTime;
import java.util.Optional;

import com.softmed.api.model.medico.Medico;
import com.softmed.api.model.pacientes.Pacientes;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idConsulta")
@Entity(name = "CONSULTA_MEDICA")
public class ConsultaMedica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idConsulta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PACIENTE")
	private Pacientes paciente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MEDICO")
	private Medico medico;
	
	private LocalDateTime dtConsulta;
	

}
