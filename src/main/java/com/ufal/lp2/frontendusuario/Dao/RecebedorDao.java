package com.ufal.lp2.frontendusuario.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ufal.lp2.frontendusuario.domain.Recebedor;

public interface RecebedorDao extends CrudRepository<Recebedor, Long> {
	
	
	 	List<Recebedor> findAll();

	    Recebedor findByName(String nomeRecebedor);

	    void deleteByName(String nomeRecebedor);
}