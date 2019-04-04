package com.ufal.lp2.frontendusuario.security;

import javax.persistence.*;

import com.ufal.lp2.frontendusuario.domain.Usuario;

@Entity
@Table(name="usuario_role")
public class UsuarioRole {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long usuarioRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public UsuarioRole(Usuario usuario, Role role) {
        this.usuario = usuario;
        this.role = role;
    }

    public UsuarioRole() {}

    public long getUsuarioRoleId() {
        return usuarioRoleId;
    }

    public void setUsuarioRoleId(long usuarioRoleId) {
        this.usuarioRoleId = usuarioRoleId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
