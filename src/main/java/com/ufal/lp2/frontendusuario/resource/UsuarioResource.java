package com.ufal.lp2.frontendusuario.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufal.lp2.frontendusuario.domain.TransacaoContaCorrente;
import com.ufal.lp2.frontendusuario.domain.TransacaoContaPoupanca;
import com.ufal.lp2.frontendusuario.domain.Usuario;
import com.ufal.lp2.frontendusuario.service.ServicoTransacao;
import com.ufal.lp2.frontendusuario.service.ServicoUsuario;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioResource {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @Autowired
    private ServicoTransacao servicoTransacao;

    @RequestMapping(value = "/usuario/todos", method = RequestMethod.GET)
    public List<Usuario> listaUsuario() {
        return servicoUsuario.procurarListaUsuario();
    }

    @RequestMapping(value = "/usuario/corrente/transacao", method = RequestMethod.GET)
    public List<TransacaoContaCorrente> getListaTransacaoContaCorente(@RequestParam("username") String username) {
        return servicoTransacao.procurarListaTransacaoContaCorrente(username);
    }

    @RequestMapping(value = "/usuario/poupanca/transacao", method = RequestMethod.GET)
    public List<TransacaoContaPoupanca> getListaTransacaoContaPoupanca(@RequestParam("username") String username) {
        return servicoTransacao.procurarListaTransacaoContaPoupanca(username);
    }

    @RequestMapping("/usuario/{username}/habilitar")
    public void habilitarUsuario(@PathVariable("username") String username) {
        servicoUsuario.habilitarUsuario(username);
    }

    @RequestMapping("/usuario/{username}/desabilitar")
    public void diableUsuario(@PathVariable("username") String username) {
        servicoUsuario.desabilitarUsuario(username);
    }
}
