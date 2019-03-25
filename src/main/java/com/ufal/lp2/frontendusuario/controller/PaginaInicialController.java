package com.ufal.lp2.frontendusuario.controller;

import java.util.Set;

import java.security.Principal;
import java.util.HashSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufal.lp2.frontendusuario.domain.Usuario;

@Controller
public class PaginaInicialController{
	
	@RequestMapping("/")
	public String paginaInicial() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
