package com.ufal.lp2.frontendusuario.service.ServicoUsuarioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufal.lp2.frontendusuario.Dao.AgendamentoDao;
import com.ufal.lp2.frontendusuario.domain.Agendamento;
import com.ufal.lp2.frontendusuario.service.ServicoAgendamento;

@Service
public class ServicoAgendamentoImpl implements ServicoAgendamento {

    @Autowired
    private AgendamentoDao agendamentoDao;

    public Agendamento criarAgendamento(Agendamento agendamento) {
       return agendamentoDao.save(agendamento);
    }

    public List<Agendamento> findAll() {
        return agendamentoDao.findAll();
    }

    public Agendamento procurarAgendamento(Long id) {
        return agendamentoDao.findById(id).get();
    }

    public void confirmarAgendamento(Long id) {
        Agendamento agendamento = procurarAgendamento(id);
        agendamento.setConfirmacao(true);
        agendamentoDao.save(agendamento);
    }
}
