package com.softmed.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softmed.api.consulta.ConsultaMedica;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaMedica, Long>{

}
