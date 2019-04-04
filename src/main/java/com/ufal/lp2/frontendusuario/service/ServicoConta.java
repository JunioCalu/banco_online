package com.ufal.lp2.frontendusuario.service;

import java.security.Principal;

import com.ufal.lp2.frontendusuario.domain.ContaCorrente;
import com.ufal.lp2.frontendusuario.domain.ContaPoupanca;

public interface ServicoConta {
	
    ContaCorrente criarContaCorrente();
    
    ContaPoupanca criarContaPoupanca();
    
    void deposito(String tipoConta, double montante, Principal principal);
    
    void saque(String tipoConta, double montante, Principal principal);
    
}
