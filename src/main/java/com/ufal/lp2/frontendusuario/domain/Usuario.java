package com.ufal.lp2.frontendusuario.domain;

import java.util.List;

public class Usuario {
	private Long usuarioId;
	private String nomeUsuario;
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
	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", nomeUsuario=" + nomeUsuario + ", primeiroNome=" + primeiroNome
				+ ", ultimoNome=" + ultimoNome + ", email=" + email + ", telefone=" + telefone + ", habilitado="
				+ habilitado + "]";
	}
	
	

}
