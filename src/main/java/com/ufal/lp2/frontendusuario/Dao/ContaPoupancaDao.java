package com.ufal.lp2.frontendusuario.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufal.lp2.frontendusuario.domain.ContaPoupanca;

/**
 * Created by z00382545 on 10/21/16.
 */
public interface ContaPoupancaDao extends CrudRepository<ContaPoupanca,Long> {
	
	List<ContaPoupanca> findByAccountNumber (int numeroConta);
}