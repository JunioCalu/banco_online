package com.ufal.lp2.frontendusuario.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class TransacaoContaCorrente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date data;
	private String descricao;
	private String tipo;
	private String status;
	private double montante;
	private BigDecimal saldoDisponivel;
	
	@ManyToOne
	@JoinColumn(name = "conta_corrente_id")
	private ContaCorrente contaCorrente;
	
	public TransacaoContaCorrente() {}

	public TransacaoContaCorrente(Date data, String descricao, String tipo, String status, double montante,
			BigDecimal saldoDisponivel, ContaCorrente contaCorrente) {
		super();
		this.data = data;
		this.descricao = descricao;
		this.tipo = tipo;
		this.status = status;
		this.montante = montante;
		this.saldoDisponivel = saldoDisponivel;
		this.contaCorrente = contaCorrente;
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

	public BigDecimal getsaldoDisponivel() {
		return saldoDisponivel;
	}

	public void setsaldoDisponivel(BigDecimal saldoDisponivel) {
		this.saldoDisponivel = saldoDisponivel;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

}
