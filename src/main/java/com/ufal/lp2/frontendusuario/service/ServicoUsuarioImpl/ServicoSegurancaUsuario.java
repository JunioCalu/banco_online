package com.ufal.lp2.frontendusuario.service.ServicoUsuarioImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ufal.lp2.frontendusuario.Dao.UsuarioDao;
import com.ufal.lp2.frontendusuario.domain.Usuario;

@Service
public class ServicoSegurancaUsuario implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(ServicoSegurancaUsuario.class);

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        if (null == usuario) {
            LOG.warn("username {} não encontrado", username);
            throw new UsernameNotFoundException("Usuarioname " + username + "não encontrado");
        }
        return usuario;
    }
}
