package com.ufal.lp2.frontendusuario.controller;

import java.security.Principal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufal.lp2.frontendusuario.domain.ContaCorrente;
import com.ufal.lp2.frontendusuario.domain.ContaPoupanca;
import com.ufal.lp2.frontendusuario.domain.TransacaoContaCorrente;
import com.ufal.lp2.frontendusuario.domain.TransacaoContaPoupanca;
import com.ufal.lp2.frontendusuario.domain.Usuario;
import com.ufal.lp2.frontendusuario.service.ServicoConta;
import com.ufal.lp2.frontendusuario.service.ServicoTransacao;
import com.ufal.lp2.frontendusuario.service.ServicoUsuario;

@Controller
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
    private ServicoUsuario servicoUsuario;
	
	@Autowired
	private ServicoConta servicoConta;
	
	@Autowired
	private ServicoTransacao servicoTransacao;
	
	@RequestMapping("/contaCorrente")
	public String contaCorrente(Model modelo, Principal principal) {

		List<TransacaoContaCorrente> listaTransacaoContaCorrente = servicoTransacao.procurarListaTransacaoContaCorrente(principal.getName());
		
		Usuario usuario = servicoUsuario.procurarPorNomeUsuario(principal.getName());
        ContaCorrente contaCorrente = usuario.getContaCorrente();

        modelo.addAttribute("contaCorrente", contaCorrente);
        modelo.addAttribute("listaTransacaoContaCorrente", listaTransacaoContaCorrente);
     
		return "contaCorrente";
	}

	@RequestMapping("/contaPoupanca")
    public String contaPoupanca(Model modelo, Principal principal) {
		List<TransacaoContaPoupanca> listaTransacaoContaPoupanca = servicoTransacao.procurarListaTransacaoContaPoupanca(principal.getName());
        Usuario usuario = servicoUsuario.procurarPorNomeUsuario(principal.getName());
        ContaPoupanca contaPoupanca = usuario.getContaPoupanca();

        modelo.addAttribute("contaPoupanca", contaPoupanca);
        modelo.addAttribute("listaTransacaoContaPoupanca", listaTransacaoContaPoupanca);
        return "contaPoupanca";
    }

	@RequestMapping(value = "/deposito", method = RequestMethod.GET)
    public String deposito(Model modelo) {

        modelo.addAttribute("tipoConta", "");
        modelo.addAttribute("montante", "");
        return "deposito";
    }

    @RequestMapping(value = "/deposito", method = RequestMethod.POST)
    public String depositoPOST(@ModelAttribute("montante") String montante, @ModelAttribute("tipoConta") String tipoConta, Principal principal) {
  		
        servicoConta.deposito(tipoConta, Double.parseDouble(montante), principal);
    
        return "redirect:/frontendUsuario";
    }
    
    @RequestMapping(value = "/saque", method = RequestMethod.GET)
    public String saque(Model modelo) {

        modelo.addAttribute("tipoConta", "");
        modelo.addAttribute("montante", "");

        return "saque";
    }

    @RequestMapping(value = "/saque", method = RequestMethod.POST)
    public String saquePOST(@ModelAttribute("montante") String montante, @ModelAttribute("tipoConta") String tipoConta, Principal principal) {
    
        servicoConta.saque(tipoConta, Double.parseDouble(montante), principal);

        return "redirect:/frontendUsuario";
    }
}
