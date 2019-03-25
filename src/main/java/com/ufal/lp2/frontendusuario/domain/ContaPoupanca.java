package com.ufal.lp2.frontendusuario.domain;

import java.math.BigDecimal;
import java.util.List;

public class ContaPoupanca {
	
	private Long id;
	private int numeroConta;
	private BigDecimal saldoConta;
	private List<TransacaoContaPoupanca> listaTransacaoContaPoupanca;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}
	public BigDecimal getSaldoConta() {
		return saldoConta;
	}
	public void setSaldoConta(BigDecimal saldoConta) {
		this.saldoConta = saldoConta;
	}
	public List<TransacaoContaPoupanca> getListaTransacaoContaPoupanca() {
		return listaTransacaoContaPoupanca;
	}
	public void setListaTransacaoContaPoupanca(List<TransacaoContaPoupanca> listaTransacaoContaPoupanca) {
		this.listaTransacaoContaPoupanca = listaTransacaoContaPoupanca;
	}
}