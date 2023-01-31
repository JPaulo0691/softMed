package com.softmed.api.consulta;

import java.time.LocalDate;
import java.time.LocalTime;

import com.softmed.api.medico.Medico;
import com.softmed.api.pacientes.Pacientes;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CONSULTA_MEDICA")
public class ConsultaMedica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConsulta;
	
	@ManyToOne
	@JoinColumn(name = "ID_PACIENTE")
	private Pacientes paciente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Medico medico;
	
	private LocalDate dtConsulta = LocalDate.now();
	private LocalTime horaConsulta = LocalTime.now();
	
	
	public ConsultaMedica(RealizarConsultaDTO dto) {
		this.paciente = new Pacientes(dto.paciente());
		this.medico = new Medico(dto.medico());
	}

}
