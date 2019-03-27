package com.ufal.lp2.frontendusuario.domain;

import java.util.Collection;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class Usuario implements UserDetails{
	
	private Long usuarioId;
	private String nomeUsuario;
    private String senha;
	private String primeiroNome;
	private String ultimoNome;
	

	private String email;
	private String telefone;
	
	private boolean habilitado=true;
	
	private ContaCorrente contaCorrente;
	
	private ContaPoupanca contaPoupanca;
	
	private List<Agendamento> listaAgendamento;
	
	private List<Recebedor> listaRecebor;
		
	
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
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
	public List<Agendamento> getListaAgendamento() {
		return listaAgendamento;
	}
	public void setListaAgendamento(List<Agendamento> listaAgendamento) {
		this.listaAgendamento = listaAgendamento;
	}
	public List<Recebedor> getListaRecebor() {
		return listaRecebor;
	}
	public void setListaRecebor(List<Recebedor> listaRecebor) {
		this.listaRecebor = listaRecebor;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", nomeUsuario=" + nomeUsuario + ", senha=" + senha
				+ ", primeiroNome=" + primeiroNome + ", ultimoNome=" + ultimoNome + ", email=" + email + ", telefone="
				+ telefone + ", habilitado=" + habilitado + ", contaCorrente=" + contaCorrente + ", contaPoupanca="
				+ contaPoupanca + ", listaAgendamento=" + listaAgendamento + ", listaRecebor=" + listaRecebor
				+ "]";
	}
	


    @Override
    public boolean isEnabled() {
        return habilitado;
    }
    
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
