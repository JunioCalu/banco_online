package com.ufal.lp2.frontendusuario.Dao;

import com.ufal.lp2.frontendusuario.domain.ContaCorrente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by z00382545 on 10/21/16.
 */
public interface ContaCorrenteDao extends CrudRepository<ContaCorrente,Long> {

	
	 List<ContaCorrente> findByAccountNumber (int numeroConta);
}
