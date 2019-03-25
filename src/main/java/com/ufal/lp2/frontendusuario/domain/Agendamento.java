package com.ufal.lp2.frontendusuario.domain;

import java.util.Date;

public class Agendamento {
	
	private Long id;
	private Date date;
	private String localizacao;
	private String descricao;
	private boolean confirmado;
	private Usuario usuario;
	
	public Agendamento(Date date, String localizacao, String descricao, boolean confirmado, Usuario usuario) {
		super();
		this.date = date;
		this.localizacao = localizacao;
		this.descricao = descricao;
		this.confirmado = confirmado;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
