package com.softmed.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softmed.api.model.consulta.ConsultaMedica;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaMedica, Long>{

	boolean existsByMedicoIdAndDtConsulta(Long idMedico, LocalDateTime data);

	boolean existsByPacienteIdAndDtConsultaBetween(Long idPaciente, LocalDateTime horaInicial, LocalDateTime horaFinal);
	
	
}
