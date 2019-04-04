package com.ufal.lp2.frontendusuario.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufal.lp2.frontendusuario.security.Authority;
import com.ufal.lp2.frontendusuario.security.UsuarioRole;

@Entity
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 2L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usuarioId", nullable = false, updatable = false)
    private Long usuarioId;
    private String username;
    private String password;
    private String primeiroNome;
    private String ultimoNome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private String telefone;

    private boolean enabled=true;

    @OneToOne
    private ContaCorrente contaCorrente;

    @OneToOne
    private ContaPoupanca contaPoupanca;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Agendamento> listaAgendamento;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recebedor> listaRecebedor;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UsuarioRole> usuarioRoles = new HashSet<>();

    public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public ContaPoupanca getContaPoupanca() {
		return contaPoupanca;
	}

	public void setContaPoupanca(ContaPoupanca contaPoupanca) {
		this.contaPoupanca = contaPoupanca;
	}

	public List<Agendamento> getAgendamentoList() {
		return listaAgendamento;
	}

	public void setAgendamentoList(List<Agendamento> listaAgendamento) {
		this.listaAgendamento = listaAgendamento;
	}

	public List<Recebedor> getListaRecebedor() {
		return listaRecebedor;
	}

	public void setListaRecebedor(List<Recebedor> listaRecebedor) {
		this.listaRecebedor = listaRecebedor;
	}

	public Set<UsuarioRole> getUsuarioRoles() {
		return usuarioRoles;
	}

	public void setUsuarioRoles(Set<UsuarioRole> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
    public String toString() {
        return "Usuario{" +
                "usuarioId=" + usuarioId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", primeiroNome='" + primeiroNome + '\'' +
                ", ultimoNome='" + ultimoNome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", listaAgendamento=" + listaAgendamento +
                ", listaRecebedor=" + listaRecebedor +
                ", usuarioRoles=" + usuarioRoles +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        usuarioRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
