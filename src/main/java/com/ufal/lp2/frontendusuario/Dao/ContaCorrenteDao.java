package com.ufal.lp2.frontendusuario.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ufal.lp2.frontendusuario.domain.ContaCorrente;

public interface ContaCorrenteDao extends CrudRepository<ContaCorrente,Long> {

    List<ContaCorrente> findByAccountNumber (int accountNumber);
}
