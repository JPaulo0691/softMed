package com.softmed.api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Service;

import com.softmed.api.exceptions.ValidacaoConsultaException;
import com.softmed.api.model.consulta.ConsultaDiariaDetalhamentoDTO;
import com.softmed.api.model.consulta.ConsultaMedica;
import com.softmed.api.model.consulta.DadosAgendamentoConsulta;
import com.softmed.api.model.consulta.DadosDetalhamentoConsulta;
import com.softmed.api.model.consulta.validacoes.ValidadorConsultas;
import com.softmed.api.model.medico.Medico;
import com.softmed.api.repository.ConsultaRepository;
import com.softmed.api.repository.MedicoRepository;
import com.softmed.api.repository.PacienteRepository;

@Service
public class ConsultaService {
	
	@Autowired
	MedicoRepository medicoRepository;
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Autowired
	ConsultaRepository consultaRepository;
	
	
	@Autowired
	private List<ValidadorConsultas> validadores; // O spring inicializará a lista de validadores
	
	private static final DateTimeFormatter DATA_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta agendaConsulta) {
		
		//Validações 
		
		if(!pacienteRepository.existsById(agendaConsulta.idPaciente())) {
			
			throw new ValidacaoConsultaException("Esse paciente não está cadastrado em nosso sistema. Favor Verificar!");			
		}
		
		if(agendaConsulta.idMedico() != null && !medicoRepository.existsById(agendaConsulta.idMedico())) {
			
			throw new ValidacaoConsultaException("Esse médico não está cadastrado em nosso sistema. Favor Verificar!");
		}
		
		// SOLID PRINCIPLES
		// PRINCÍPIO DA RESPONSABILIDADE ÚNICA
		// PRINCÍPIO ABERTO - FECHADO : CLASSE ABERTA PARA EXTENSÃO E FECHADA PARA MODIFICAÇÃO
		// PRINCÍPIO DA INVERSÃO DE DEPENDÊNCIA: PQ A SERVICE DEPENDE DE UMA ABSTRAÇÃO QUE É A INTERFACE 
		validadores.forEach(validar -> validar.validar(agendaConsulta));
		
		var paciente = pacienteRepository.findById(agendaConsulta.idPaciente()).get(); // aqui eu pego o objeto pelo id, usando o repository
		
		var medico = escolherMedico(agendaConsulta);
		
		if (medico == null) {
            throw new ValidacaoConsultaException("Não existe médico disponível nessa data!");
        }
		
		var consulta = new ConsultaMedica(null,paciente,medico, agendaConsulta.data());
		
		consultaRepository.save(consulta);
		
		return new DadosDetalhamentoConsulta(consulta);
	}

	private Medico escolherMedico(DadosAgendamentoConsulta agendaConsulta) {
		
		if(agendaConsulta.idMedico() != null) {
			return medicoRepository.getReferenceById(agendaConsulta.idMedico());
		}
		
		if(agendaConsulta.especialidade() == null) {
			throw new ValidacaoConsultaException("Especialidade é obrigatória quando o médico não for escolhido.");
		}
		
		return medicoRepository.escolherMedicoLivreNaData(agendaConsulta.especialidade().toString(), agendaConsulta.data());
	}
	
	
	public Page<ConsultaDiariaDetalhamentoDTO> relatorioDeConsultas(Pageable paginacao
			                                                       ,@DateTimeFormat(iso = ISO.DATE_TIME)LocalDateTime dataInicial
			                                                       ,@DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataFinal){
		
		return consultaRepository.findAllByDtConsultaBetween(paginacao,dataInicial, dataFinal)
				                 .map(ConsultaDiariaDetalhamentoDTO::new);
	}
	
}
