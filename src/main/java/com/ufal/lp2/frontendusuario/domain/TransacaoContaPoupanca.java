package com.ufal.lp2.frontendusuario.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TransacaoContaPoupanca {
	
	private Long id;
	private Date data;
	private String descricao;
	private String tipo;
	private String status;
	private double montante;
	private BigDecimal saldoDisponivel;
	
	
	public TransacaoContaPoupanca(Date data, String descricao, String tipo, String status, double montante,
			BigDecimal saldoDisponivel) {
		super();
		this.data = data;
		this.descricao = descricao;
		this.tipo = tipo;
		this.status = status;
		this.montante = montante;
		this.saldoDisponivel = saldoDisponivel;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getMontante() {
		return montante;
	}
	public void setMontante(double montante) {
		this.montante = montante;
	}
	public BigDecimal getSaldoDisponivel() {
		return saldoDisponivel;
	}
	public void setSaldoDisponivel(BigDecimal saldoDisponivel) {
		this.saldoDisponivel = saldoDisponivel;
	}
	
	
}
