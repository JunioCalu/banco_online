package com.ufal.lp2.frontendusuario.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufal.lp2.frontendusuario.domain.Agendamento;
import com.ufal.lp2.frontendusuario.domain.Usuario;
import com.ufal.lp2.frontendusuario.service.ServicoAgendamento;
import com.ufal.lp2.frontendusuario.service.ServicoUsuario;

@Controller
@RequestMapping("/agendamento")
public class AgendamentoController {

	@Autowired
	private ServicoAgendamento agendamentoService;

	@Autowired
	private ServicoUsuario servicoUsuario;

	@RequestMapping(value = "/criar",method = RequestMethod.GET)
	public String criarAgendamento(Model modelo) {

		Agendamento agendamento = new Agendamento();
		modelo.addAttribute("agendamento", agendamento);
		modelo.addAttribute("stringData", "");

		return "agendamento";
	}

	@RequestMapping(value = "/criar",method = RequestMethod.POST)
	public String criarAgendamentoPost(@ModelAttribute("agendamento") Agendamento agendamento, @ModelAttribute("stringData") String data, Model modelo, Principal principal) throws ParseException {
		
		SimpleDateFormat f = new SimpleDateFormat("MM-dd-yyyy hh:mm");
		Date data1 = f.parse( data );
		agendamento.setData(data1);

		Usuario usuario = servicoUsuario.procurarPorNomeUsuario(principal.getName());
		agendamento.setUsuario(usuario);

		agendamentoService.criarAgendamento(agendamento);

		return "redirect:/frontendUsuario";
	}
}
