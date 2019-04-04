package com.ufal.lp2.frontendusuario.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ufal.lp2.frontendusuario.domain.TransacaoContaCorrente;

public interface TransacaoContaCorrenteDao extends CrudRepository<TransacaoContaCorrente, Long> {

    List<TransacaoContaCorrente> findAll();
}
