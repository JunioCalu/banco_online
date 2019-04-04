package com.ufal.lp2.frontendusuario.service;

import java.util.List;

import com.ufal.lp2.frontendusuario.domain.Agendamento;

public interface ServicoAgendamento {
    
	Agendamento criarAgendamento(Agendamento agendamento);

    List<Agendamento> findAll();

    Agendamento procurarAgendamento(Long id);

    void confirmarAgendamento(Long id);
}
