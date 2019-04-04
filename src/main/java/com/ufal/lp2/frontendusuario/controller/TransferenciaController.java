package com.ufal.lp2.frontendusuario.controller;

import java.security.Principal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufal.lp2.frontendusuario.domain.ContaCorrente;
import com.ufal.lp2.frontendusuario.domain.ContaPoupanca;
import com.ufal.lp2.frontendusuario.domain.Recebedor;
import com.ufal.lp2.frontendusuario.domain.Usuario;
import com.ufal.lp2.frontendusuario.service.ServicoTransacao;
import com.ufal.lp2.frontendusuario.service.ServicoUsuario;

@Controller
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private ServicoTransacao servicoTransacao;

	@Autowired
	private ServicoUsuario servicoUsuario;

	@RequestMapping(value = "/entreContras", method = RequestMethod.GET)
	public String entreContas(Model modelo) {

		modelo.addAttribute("transferirDe", "");
		modelo.addAttribute("transferirPara", "");
		modelo.addAttribute("montante", "");

		return "entreContas";
	}

	@RequestMapping(value = "/entreContas", method = RequestMethod.POST)
	public String entreContasPost(
			@ModelAttribute("transferirDe") String transferirDe,
			@ModelAttribute("transferirPara") String transferirPara,
			@ModelAttribute("montante") String montante,
			Principal principal
	) throws Exception {

		Usuario usuario = servicoUsuario.procurarPorNomeUsuario(principal.getName());
		ContaCorrente contaCorrente = usuario.getContaCorrente();
		ContaPoupanca contaPoupanca = usuario.getContaPoupanca();
		servicoTransacao.transferenciaEntreContas(transferirDe, transferirPara, montante, contaCorrente, contaPoupanca);

		return "redirect:/frontendUsuario";
	}
	
	@RequestMapping(value = "/recebedor", method = RequestMethod.GET)
	public String recebedor(Model modelo, Principal principal) {
		
		List<Recebedor> listaRecebedor = servicoTransacao.procurarListaRecebedor(principal);

		Recebedor recebedor = new Recebedor();

		modelo.addAttribute("listaRecebedor", listaRecebedor);
		modelo.addAttribute("recebedor", recebedor);

		return "recebedor";
	}

	@RequestMapping(value = "/recebedor/salvar", method = RequestMethod.POST)
	public String recebedorPost(@ModelAttribute("recebedor") Recebedor recebedor, Principal principal) {
		
		Usuario usuario = servicoUsuario.procurarPorNomeUsuario(principal.getName());
		recebedor.setUsuario(usuario);
		servicoTransacao.salvarRecebedor(recebedor);

		return "redirect:/transfer/recebedor";
	}

	@RequestMapping(value = "/recebedor/salvar", method = RequestMethod.GET)
	public String editarRecebedor(@RequestParam(value = "nomeRecebedor") String nomeRecebedor, Model modelo, Principal principal){
		

		Recebedor recebedor = servicoTransacao.procurarPorNomeRecebedor(nomeRecebedor);
		List<Recebedor> listaRecebedor = servicoTransacao.procurarListaRecebedor(principal);

		modelo.addAttribute("listaRecebedor", listaRecebedor);
		modelo.addAttribute("recebedor", recebedor);

		return "recebedor";
	}

	@RequestMapping(value = "/recebedor/deletar", method = RequestMethod.GET)
	@Transactional
	public String deleteRecebedor(@RequestParam(value = "nomeRecebedor") String nomeRecebedor, Model modelo, Principal principal){
	
		servicoTransacao.deletarPorNomeRecebedor(nomeRecebedor);

		List<Recebedor> listaRecebedor = servicoTransacao.procurarListaRecebedor(principal);

		Recebedor recebedor = new Recebedor();
		modelo.addAttribute("recebedor", recebedor);
		modelo.addAttribute("listaRecebedor", listaRecebedor);

		return "recebedor";
	}

	@RequestMapping(value = "/paraOutraPessoa",method = RequestMethod.GET)
	public String paraOutraPessoa(Model modelo, Principal principal) {

		List<Recebedor> listaRecebedor = servicoTransacao.procurarListaRecebedor(principal);

		modelo.addAttribute("listaRecebedor", listaRecebedor);
		modelo.addAttribute("tipoConta", "");

		return "paraOutraPessoa";
	}

	@RequestMapping(value =  "/paraOutraPessoa",method = RequestMethod.POST)
	public String paraOutraPessoaPost(@ModelAttribute("nomeRecebedor") String nomeRecebedor, @ModelAttribute("tipoConta") String tipoConta, @ModelAttribute("montante") String montante, Principal principal) {

		Usuario usuario = servicoUsuario.procurarPorNomeUsuario(principal.getName());
		Recebedor recebedor = servicoTransacao.procurarPorNomeRecebedor(nomeRecebedor);
		servicoTransacao.transferirParaOutraPessoa(recebedor, tipoConta, montante, usuario.getContaCorrente(), usuario.getContaPoupanca());

		return "redirect:/frontendUsuario";
	}
}
