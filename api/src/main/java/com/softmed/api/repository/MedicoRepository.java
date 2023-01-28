package com.softmed.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softmed.api.medico.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{
	
	Optional<Medico> findByCrm(String crm);

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
