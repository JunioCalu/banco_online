package com.ufal.lp2.frontendusuario.controller;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufal.lp2.frontendusuario.domain.Usuario;
import com.ufal.lp2.frontendusuario.service.ServicoUsuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	

	@Autowired
	private ServicoUsuario servicoUsuario;

	@RequestMapping(value = "/perfil", method = RequestMethod.GET)
	public String perfil(Principal principal, Model modelo) {
		

		Usuario usuario = servicoUsuario.procurarPorNomeUsuario(principal.getName());

		modelo.addAttribute("usuario", usuario);

		return "perfil";
	}

	@RequestMapping(value = "/perfil", method = RequestMethod.POST)
	public String perfilPost(@ModelAttribute("usuario") Usuario newUsuario, Model modelo) {

		Usuario usuario = servicoUsuario.procurarPorNomeUsuario(newUsuario.getUsername());
		usuario.setUsername(newUsuario.getUsername());
		usuario.setPrimeiroNome(newUsuario.getPrimeiroNome());
		usuario.setUltimoNome(newUsuario.getUltimoNome());
		usuario.setEmail(newUsuario.getEmail());
		usuario.setTelefone(newUsuario.getTelefone());

		modelo.addAttribute("usuario", usuario);

		servicoUsuario.salvarUsuario(usuario);

		return "perfil";
	}
}
