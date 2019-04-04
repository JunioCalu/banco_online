package com.ufal.lp2.frontendusuario.service.ServicoUsuarioImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ufal.lp2.frontendusuario.Dao.RoleDao;
import com.ufal.lp2.frontendusuario.Dao.UsuarioDao;
import com.ufal.lp2.frontendusuario.domain.Usuario;
import com.ufal.lp2.frontendusuario.security.UsuarioRole;
import com.ufal.lp2.frontendusuario.service.ServicoConta;
import com.ufal.lp2.frontendusuario.service.ServicoUsuario;

@Service
@Transactional
public class ServicoUsuarioImpl implements ServicoUsuario {
	
	private static final Logger LOG = LoggerFactory.getLogger(ServicoUsuario.class);
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private ServicoConta servicoConta;
	
	public void save(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    public Usuario procurarPorNomeUsuario(String username) {
        return usuarioDao.findByUsername(username);
    }

    public Usuario findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }
    
    
    public Usuario criarUsuario(Usuario usuario, Set<UsuarioRole> usuarioRoles) {
        Usuario localUsuario = usuarioDao.findByUsername(usuario.getUsername());

        if (localUsuario != null) {
            LOG.info("Usuario usando username {} já existe. Nada será feito. ", usuario.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(encryptedPassword);

            Set<UsuarioRole> setUsuarioRoles = usuario.getUsuarioRoles();
            if (null == setUsuarioRoles)
            		setUsuarioRoles = new HashSet<UsuarioRole>();
            	setUsuarioRoles.addAll(usuarioRoles);
            	usuario.setUsuarioRoles(setUsuarioRoles);

            for (UsuarioRole ur : usuarioRoles) {
                roleDao.save(ur.getRole());
            }

            usuario.setContaCorrente(servicoConta.criarContaCorrente());
            usuario.setContaPoupanca(servicoConta.criarContaPoupanca());

            localUsuario = usuarioDao.save(usuario);
        }

        return localUsuario;
    }
    
    public boolean checkUserExists(String username, String email){
        return (checkUsernameExists(username) || checkEmailExists(username));
    }

    public boolean checkUsernameExists(String username) {
        return (null != procurarPorNomeUsuario(username));
    }
    
    public boolean checkEmailExists(String email) {
        return (null != findByEmail(email));
    }

    public Usuario salvarUsuario (Usuario usuario) {
        return usuarioDao.save(usuario);
    }
    
    public List<Usuario> procurarListaUsuario() {
        return usuarioDao.findAll();
    }

    public void habilitarUsuario (String username) {
        Usuario usuario = procurarPorNomeUsuario(username);
        usuario.setEnabled(true);
        usuarioDao.save(usuario);
    }

    public void desabilitarUsuario (String username) {
        Usuario usuario = procurarPorNomeUsuario(username);
        usuario.setEnabled(false);
        System.out.println(usuario.isEnabled());
        usuarioDao.save(usuario);
        System.out.println(username + " é desabilitado.");
    }
}
