package com.ufal.lp2.frontendusuario.Dao;

import org.springframework.data.repository.CrudRepository;

import com.ufal.lp2.frontendusuario.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {
    
    Role findByName(String name);
}
