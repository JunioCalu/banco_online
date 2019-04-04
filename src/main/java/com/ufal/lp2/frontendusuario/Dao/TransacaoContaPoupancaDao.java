package com.ufal.lp2.frontendusuario.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ufal.lp2.frontendusuario.domain.TransacaoContaPoupanca;

public interface TransacaoContaPoupancaDao extends CrudRepository<TransacaoContaPoupanca, Long> {

    List<TransacaoContaPoupanca> findAll();
}

