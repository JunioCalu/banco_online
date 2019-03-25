package com.ufal.lp2.frontendusuario.domain;


import java.math.BigDecimal;
import java.util.List;

public class ContaCorrente {
	
	private Long id;
	private int numeroConta;
	private BigDecimal saldoConta;
	private List<TransacaoContaCorrente> listaTransacaoContaCorrente;
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
	public List<TransacaoContaCorrente> getListaTransacaoContaCorrente() {
		return listaTransacaoContaCorrente;
	}
	public void setListaTransacaoContaCorrente(List<TransacaoContaCorrente> listaTransacaoContaCorrente) {
		this.listaTransacaoContaCorrente = listaTransacaoContaCorrente;
	}
	
}