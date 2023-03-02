package com.softmed.api.repository;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Repository;

import com.softmed.api.model.consulta.ConsultaMedica;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaMedica, Long>{

	boolean existsByMedicoIdAndDtConsulta(Long idMedico, LocalDateTime data);

	boolean existsByPacienteIdAndDtConsultaBetween(Long idPaciente, LocalDateTime horaInicial, LocalDateTime horaFinal);
	
	Page<ConsultaMedica> findAllByDtConsultaBetween(Pageable paginacao
			                                ,@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataInicial
			                                ,@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataFinal);
}
