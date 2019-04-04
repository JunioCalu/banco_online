package com.ufal.lp2.frontendusuario.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ufal.lp2.frontendusuario.domain.ContaPoupanca;

public interface ContaPoupancaDao extends CrudRepository<ContaPoupanca, Long> {

    List<ContaPoupanca> findByAccountNumber (int accountNumber);
}
