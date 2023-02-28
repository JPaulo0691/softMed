package com.softmed.api.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softmed.api.enums.Especialidade;
import com.softmed.api.model.medico.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{
	
	Optional<Medico> findByCrm(String crm);
	
	Optional<Medico> findByCrmAndAtivo(String crm, boolean ativo);

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);
	
	
	@Query(value = "SELECT * \r\n"
			+ "  FROM MEDICOS M\r\n"
			+ "  WHERE M.ATIVO = 1\r\n"
			+ "  AND M.ESPECIALIDADE = :especialidade\r\n"
			+ "  AND M.ID NOT IN (\r\n"
			+ "      SELECT CONS.ID_MEDICO \r\n"
			+ "        FROM CONSULTA_MEDICA CONS \r\n"
			+ "       WHERE CONS.DT_CONSULTA = TO_DATE(:dtConsulta, 'YYYY-MM-DD HH24:MI:SS')\r\n"
			+ "  )\r\n"
			+ "  ORDER BY DBMS_RANDOM.RANDOM \r\n"
			+ "  FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
	Medico escolherMedicoLivreNaData(@Param("especialidade")Especialidade especialidade,
			                        @Param("dtConsulta")LocalDateTime dtConsulta);
	
	@Query(value = "select M.ATIVO \r\n"
			+ "       from MEDICOS M \r\n"
			+ "      where M.ID = :id", nativeQuery = true)
	Boolean findAtivoById(@Param("id")Long id);
}
