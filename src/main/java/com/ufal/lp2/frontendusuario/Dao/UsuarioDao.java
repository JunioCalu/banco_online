package com.ufal.lp2.frontendusuario.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ufal.lp2.frontendusuario.domain.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {
    
	Usuario findByUsername(String username);
	
    Usuario findByEmail(String email);
    
    List<Usuario> findAll();
}
