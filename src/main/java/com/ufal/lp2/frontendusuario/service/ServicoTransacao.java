package com.ufal.lp2.frontendusuario.service;

import java.security.Principal;
import java.util.List;

import com.ufal.lp2.frontendusuario.domain.ContaCorrente;
import com.ufal.lp2.frontendusuario.domain.ContaPoupanca;
import com.ufal.lp2.frontendusuario.domain.Recebedor;
import com.ufal.lp2.frontendusuario.domain.TransacaoContaCorrente;
import com.ufal.lp2.frontendusuario.domain.TransacaoContaPoupanca;

public interface ServicoTransacao {
    
	List<TransacaoContaCorrente> procurarListaTransacaoContaCorrente(String username);

    List<TransacaoContaPoupanca> procurarListaTransacaoContaPoupanca(String username);

    void salvarTransacaoDepositoContaCorrente(TransacaoContaCorrente transacaoContaCorrente);

    void salvarTransacaoDepositoContaPoupanca(TransacaoContaPoupanca transacaoContaPoupanca);
    
    void salvarTransacaoSaqueContaCorrente(TransacaoContaCorrente transacaoContaCorrente);
    
    void salvarTransacaoSaqueContaPoupanca(TransacaoContaPoupanca transacaoContaPoupanca);
    
    void transferenciaEntreContas(String transferirDe, String transferirPara, String montante, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) throws Exception;
    
    List<Recebedor> procurarListaRecebedor(Principal principal);

    Recebedor salvarRecebedor(Recebedor recebedor);

    Recebedor procurarPorNomeRecebedor(String nomeRecebedor);

    void deletarPorNomeRecebedor(String nomeRecebedor);
    
    void transferirParaOutraPessoa(Recebedor recebedor, String tipoConta, String montante, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca);
}
