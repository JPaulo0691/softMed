package com.softmed.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softmed.api.model.pacientes.Pacientes;

@Repository
public interface PacienteRepository extends JpaRepository<Pacientes, Long>{
	
	Optional<Pacientes> findByCpf(String cpf);
	
	@Query(value = "select p.ativo "
			+ "       from PACIENTES p "
			+ "     where p.id = :id", nativeQuery = true)
	Boolean findAtivoById(@Param("id")Long id);
	
	
}
