package com.ufal.lp2.frontendusuario.service;

import java.util.List;
import java.util.Set;

import com.ufal.lp2.frontendusuario.domain.Usuario;
import com.ufal.lp2.frontendusuario.security.UsuarioRole;

public interface ServicoUsuario {
	
    Usuario procurarPorNomeUsuario(String username);

    Usuario findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
    
    void save (Usuario usuario);
    
    Usuario criarUsuario(Usuario usuario, Set<UsuarioRole> usuarioRoles);
    
    Usuario salvarUsuario (Usuario usuario); 
    
    List<Usuario> procurarListaUsuario();

    void habilitarUsuario (String username);

    void desabilitarUsuario (String username);
    
}
