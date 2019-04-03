package com.ufal.lp2.frontendusuario.domain;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ContaCorrente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int accountNumber;
	private BigDecimal saldoConta;
	
	@OneToMany(mappedBy = "contaCorrente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<TransacaoContaCorrente> listaTransacaoContaCorrente;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}