package com.ufal.lp2.frontendusuario.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufal.lp2.frontendusuario.domain.Agendamento;
import com.ufal.lp2.frontendusuario.service.ServicoAgendamento;

@RestController
@RequestMapping("/api/agendamento")
@PreAuthorize("hasRole('ADMIN')")
public class AgendamentoResource {

    @Autowired
    private ServicoAgendamento agendamentoService;

    @RequestMapping("/todos")
    public List<Agendamento> procurarListaAgendamento() {
        List<Agendamento> listaAgendamento = agendamentoService.findAll();

        return listaAgendamento;
    }

    @RequestMapping("/{id}/confirmar")
    public void confirmarAgendamento(@PathVariable("id") Long id) {
        agendamentoService.confirmarAgendamento(id);
    }
}
