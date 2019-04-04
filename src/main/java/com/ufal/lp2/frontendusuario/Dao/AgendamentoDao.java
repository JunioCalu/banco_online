package com.ufal.lp2.frontendusuario.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ufal.lp2.frontendusuario.domain.Agendamento;

public interface AgendamentoDao extends CrudRepository<Agendamento, Long> {

    List<Agendamento> findAll();
}
