package com.softmed.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softmed.api.pacientes.Pacientes;

@Repository
public interface PacienteRepository extends JpaRepository<Pacientes, Long>{
	
	Optional<Pacientes> findByCpf(String cpf);
	
	
}
