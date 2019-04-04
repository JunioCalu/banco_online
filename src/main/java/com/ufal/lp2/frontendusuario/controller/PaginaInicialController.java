package com.ufal.lp2.frontendusuario.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufal.lp2.frontendusuario.Dao.RoleDao;
import com.ufal.lp2.frontendusuario.domain.ContaCorrente;
import com.ufal.lp2.frontendusuario.domain.ContaPoupanca;
import com.ufal.lp2.frontendusuario.domain.Usuario;
import com.ufal.lp2.frontendusuario.security.UsuarioRole;
import com.ufal.lp2.frontendusuario.service.ServicoUsuario;

@Controller
public class PaginaInicialController {
	
	@Autowired
	private ServicoUsuario servicoUsuario;
	
	@Autowired
    private RoleDao roleDao;
	
	@RequestMapping("/")
	public String paginaInicial() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
    public String index() {
        return "index";
    }
	
	@RequestMapping(value = "/inscrever", method = RequestMethod.GET)
    public String inscrever(Model modelo) {
        Usuario usuario = new Usuario();

        modelo.addAttribute("usuario", usuario);
        return "inscrever";
    }
	
	@RequestMapping(value = "/inscrever", method = RequestMethod.POST)
    public String inscreverPost(@ModelAttribute("usuario") Usuario usuario,  Model modelo) {
		
        if(servicoUsuario.checkUserExists(usuario.getUsername(), usuario.getEmail()))  {

            if (servicoUsuario.checkEmailExists(usuario.getEmail())) {
                modelo.addAttribute("emailExists", true);
            }

            if (servicoUsuario.checkUsernameExists(usuario.getUsername())) {
                modelo.addAttribute("usernameExists", true);
            }
            return "inscrever";
        } else {
        	 Set<UsuarioRole> usuarioRoles = new HashSet<>();
             usuarioRoles.add(new UsuarioRole(usuario, roleDao.findByName("ROLE_USER")));

            servicoUsuario.criarUsuario(usuario, usuarioRoles);
  
            return "redirect:/";
        }
    }
	
	@RequestMapping("/frontendUsuario")
	public String frontendUsuario(Principal principal, Model modelo) {
		
        Usuario usuario = servicoUsuario.procurarPorNomeUsuario(principal.getName());
        ContaCorrente contaCorrente = usuario.getContaCorrente();
        ContaPoupanca contaPoupanca = usuario.getContaPoupanca();

        modelo.addAttribute("contaCorrente", contaCorrente);
        modelo.addAttribute("contaPoupanca", contaPoupanca);
        return "frontendUsuario";
    }
}
