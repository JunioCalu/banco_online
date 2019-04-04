package com.ufal.lp2.frontendusuario.service.ServicoUsuarioImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ufal.lp2.frontendusuario.Dao.ContaCorrenteDao;
import com.ufal.lp2.frontendusuario.Dao.ContaPoupancaDao;
import com.ufal.lp2.frontendusuario.controller.UsuarioController;
import com.ufal.lp2.frontendusuario.domain.ContaCorrente;
import com.ufal.lp2.frontendusuario.domain.ContaPoupanca;
import com.ufal.lp2.frontendusuario.domain.TransacaoContaCorrente;
import com.ufal.lp2.frontendusuario.domain.TransacaoContaPoupanca;
import com.ufal.lp2.frontendusuario.domain.Usuario;
import com.ufal.lp2.frontendusuario.service.ServicoConta;
import com.ufal.lp2.frontendusuario.service.ServicoTransacao;
import com.ufal.lp2.frontendusuario.service.ServicoUsuario;

@Service
public class ServicoContaImpl implements ServicoConta {

	private static int proximoAccountNumber = 4213212;

    @Autowired
    private ContaCorrenteDao contaCorrenteDao;

    @Autowired
    private ContaPoupancaDao contaPoupancaDao;

    @Autowired
    private ServicoUsuario servicoUsuario;
    
    @Autowired
    private ServicoTransacao servicoTransacao;

    public ContaCorrente criarContaCorrente() {
    		int iniciarNumeroConta = 0;
    		boolean existeContaBolean = true;
    		List<ContaCorrente> listaProcurarPorAccountNumber;
    		while (existeContaBolean) {
    			iniciarNumeroConta = geradorConta();
        		listaProcurarPorAccountNumber = contaCorrenteDao.findByAccountNumber(iniciarNumeroConta);
    			if (CollectionUtils.isEmpty(listaProcurarPorAccountNumber))
    				existeContaBolean = false;
    		}

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setSaldoConta(new BigDecimal("0.0"));
        contaCorrente.setAccountNumber(iniciarNumeroConta);

        contaCorrenteDao.save(contaCorrente);

        listaProcurarPorAccountNumber = contaCorrenteDao.findByAccountNumber(contaCorrente.getAccountNumber());
        if (!CollectionUtils.isEmpty(listaProcurarPorAccountNumber))
        		return listaProcurarPorAccountNumber.get(0);
        else
        		return null;
    }

    public ContaPoupanca criarContaPoupanca() {
		int iniciarNumeroConta = 0;
		boolean existeContaBolean = true;
		List<ContaPoupanca> listaProcurarPorAccountNumber;
		while (existeContaBolean) {
			iniciarNumeroConta = geradorConta();
    			listaProcurarPorAccountNumber = contaPoupancaDao.findByAccountNumber(iniciarNumeroConta);
			if (CollectionUtils.isEmpty(listaProcurarPorAccountNumber))
				existeContaBolean = false;
		}

        ContaPoupanca contaPoupanca = new ContaPoupanca();
        contaPoupanca.setSaldoConta(new BigDecimal("0.0"));
        contaPoupanca.setAccountNumber(iniciarNumeroConta);

        contaPoupancaDao.save(contaPoupanca);

        listaProcurarPorAccountNumber = contaPoupancaDao.findByAccountNumber(contaPoupanca.getAccountNumber());
        if (!CollectionUtils.isEmpty(listaProcurarPorAccountNumber))
        		return listaProcurarPorAccountNumber.get(0);
        else
        		return null;
    }
    
    public void deposito(String tipoConta, double montante, Principal principal) {
        Usuario usuario = servicoUsuario.procurarPorNomeUsuario(principal.getName());

        if (tipoConta.equalsIgnoreCase("Corrente")) {
            ContaCorrente contaCorrente = usuario.getContaCorrente();
            contaCorrente.setSaldoConta(contaCorrente.getSaldoConta().add(new BigDecimal(montante)));
            contaCorrenteDao.save(contaCorrente);

            Date data = new Date();

            TransacaoContaCorrente transacaoContaCorrente = new TransacaoContaCorrente(data, "Depositando na Conta Corrente", "Conta", "Finalizado", montante, contaCorrente.getSaldoConta(), contaCorrente);
            servicoTransacao.salvarTransacaoDepositoContaCorrente(transacaoContaCorrente);

            
        } else if (tipoConta.equalsIgnoreCase("Poupanca")) {
            ContaPoupanca contaPoupanca = usuario.getContaPoupanca();
            contaPoupanca.setSaldoConta(contaPoupanca.getSaldoConta().add(new BigDecimal(montante)));
            contaPoupancaDao.save(contaPoupanca);

            Date data = new Date();
            TransacaoContaPoupanca transacaoContaPoupanca = new TransacaoContaPoupanca(data, "Depositando na Conta Poupança", "Conta", "Finalizado", montante, contaPoupanca.getSaldoConta(), contaPoupanca);
            servicoTransacao.salvarTransacaoDepositoContaPoupanca(transacaoContaPoupanca);

        }
    }

    public void saque(String tipoConta, double montante, Principal principal) {
        Usuario usuario = servicoUsuario.procurarPorNomeUsuario(principal.getName());

        if (tipoConta.equalsIgnoreCase("Corrente")) {
            ContaCorrente contaCorrente = usuario.getContaCorrente();
            contaCorrente.setSaldoConta(contaCorrente.getSaldoConta().subtract(new BigDecimal(montante)));
            contaCorrenteDao.save(contaCorrente);

            Date data = new Date();

            TransacaoContaCorrente transacaoContaCorrente = new TransacaoContaCorrente(data, "Fazendo um saque da Conta corrente", "Conta", "Finalizado", montante, contaCorrente.getSaldoConta(), contaCorrente);
            servicoTransacao.salvarTransacaoSaqueContaCorrente(transacaoContaCorrente);
   
        } else if (tipoConta.equalsIgnoreCase("Poupanca")) {
            ContaPoupanca contaPoupanca = usuario.getContaPoupanca();
            contaPoupanca.setSaldoConta(contaPoupanca.getSaldoConta().subtract(new BigDecimal(montante)));
            contaPoupancaDao.save(contaPoupanca);

            Date data = new Date();
            TransacaoContaPoupanca transacaoContaPoupanca = new TransacaoContaPoupanca(data, "Fazendo um saque da Conta Poupança ", "Conta", "Finalizado", montante, contaPoupanca.getSaldoConta(), contaPoupanca);
            servicoTransacao.salvarTransacaoSaqueContaPoupanca(transacaoContaPoupanca);
        }
    }

    private int geradorConta() {
        return ++proximoAccountNumber;
    }

}
