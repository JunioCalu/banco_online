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


    public TransacaoContaCorrente(Date data, String descricao, String tipo, String status, double montante, BigDecimal saldoDisponivel, ContaCorrente contaCorrente) {
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

    public Date getDate() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescription() {
        return descricao;
    }

    public void setDescription(String descricao) {
        this.descricao = descricao;
    }

    public String getType() {
        return tipo;
    }

    public void setType(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return montante;
    }

    public void setAmount(double montante) {
        this.montante = montante;
    }

    public BigDecimal getAvailableBalance() {
        return saldoDisponivel;
    }

    public void setAvailableBalance(BigDecimal saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

}
