package com.ufal.lp2.frontendusuario.service.ServicoUsuarioImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufal.lp2.frontendusuario.Dao.ContaCorrenteDao;
import com.ufal.lp2.frontendusuario.Dao.ContaPoupancaDao;
import com.ufal.lp2.frontendusuario.Dao.RecebedorDao;
import com.ufal.lp2.frontendusuario.Dao.TransacaoContaCorrenteDao;
import com.ufal.lp2.frontendusuario.Dao.TransacaoContaPoupancaDao;
import com.ufal.lp2.frontendusuario.domain.ContaCorrente;
import com.ufal.lp2.frontendusuario.domain.ContaPoupanca;
import com.ufal.lp2.frontendusuario.domain.Recebedor;
import com.ufal.lp2.frontendusuario.domain.TransacaoContaCorrente;
import com.ufal.lp2.frontendusuario.domain.TransacaoContaPoupanca;
import com.ufal.lp2.frontendusuario.domain.Usuario;
import com.ufal.lp2.frontendusuario.service.ServicoTransacao;
import com.ufal.lp2.frontendusuario.service.ServicoUsuario;

@Service
public class ServicoTransacaoImpl implements ServicoTransacao {
	
	@Autowired
	private ServicoUsuario servicoUsuario;
	
	@Autowired
	private TransacaoContaCorrenteDao transacaoContaCorrenteDao;
	
	@Autowired
	private TransacaoContaPoupancaDao transacaoContaPoupancaDao;
	
	@Autowired
	private ContaCorrenteDao contaCorrenteDao;
	
	@Autowired
	private ContaPoupancaDao contaPoupancaDao;
	
	@Autowired
	private RecebedorDao recebedorDao;
	

	public List<TransacaoContaCorrente> procurarListaTransacaoContaCorrente(String username){
        Usuario usuario = servicoUsuario.procurarPorNomeUsuario(username);
        List<TransacaoContaCorrente> listaTransacaoContaCorrente = usuario.getContaCorrente().getListaTransacaoContaCorrente();

        return listaTransacaoContaCorrente;
    }

    public List<TransacaoContaPoupanca> procurarListaTransacaoContaPoupanca(String username) {
        Usuario usuario = servicoUsuario.procurarPorNomeUsuario(username);
        List<TransacaoContaPoupanca> listaTransacaoContaPoupanca = usuario.getContaPoupanca().getListaTransacaoContaPoupanca();

        return listaTransacaoContaPoupanca;
    }

    public void salvarTransacaoDepositoContaCorrente(TransacaoContaCorrente transacaoContaCorrente) {
        transacaoContaCorrenteDao.save(transacaoContaCorrente);
    }

    public void salvarTransacaoDepositoContaPoupanca(TransacaoContaPoupanca transacaoContaPoupanca) {
        transacaoContaPoupancaDao.save(transacaoContaPoupanca);
    }
    
    public void salvarTransacaoSaqueContaCorrente(TransacaoContaCorrente transacaoContaCorrente) {
        transacaoContaCorrenteDao.save(transacaoContaCorrente);
    }

    public void salvarTransacaoSaqueContaPoupanca(TransacaoContaPoupanca transacaoContaPoupanca) {
        transacaoContaPoupancaDao.save(transacaoContaPoupanca);
    }
    
    public void transferenciaEntreContas(String transferirDe, String transferirPara, String montante, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) throws Exception {
        if (transferirDe.equalsIgnoreCase("Corrente") && transferirPara.equalsIgnoreCase("Poupanca")) {
            contaCorrente.setSaldoConta(contaCorrente.getSaldoConta().subtract(new BigDecimal(montante)));
            contaPoupanca.setSaldoConta(contaPoupanca.getSaldoConta().add(new BigDecimal(montante)));
            contaCorrenteDao.save(contaCorrente);
            contaPoupancaDao.save(contaPoupanca);

            Date data = new Date();

            TransacaoContaCorrente transacaoContaCorrente = new TransacaoContaCorrente(data, "Transferencia entre contas de"+transferirDe+" para "+transferirPara, "Conta", "Finalizado", Double.parseDouble(montante), contaCorrente.getSaldoConta(), contaCorrente);
            transacaoContaCorrenteDao.save(transacaoContaCorrente);
        } else if (transferirDe.equalsIgnoreCase("Poupanca") && transferirPara.equalsIgnoreCase("Corrente")) {
            contaCorrente.setSaldoConta(contaCorrente.getSaldoConta().add(new BigDecimal(montante)));
            contaPoupanca.setSaldoConta(contaPoupanca.getSaldoConta().subtract(new BigDecimal(montante)));
            contaCorrenteDao.save(contaCorrente);
            contaPoupancaDao.save(contaPoupanca);

            Date data = new Date();

            TransacaoContaPoupanca transacaoContaPoupanca = new TransacaoContaPoupanca(data, "Transferencia entre contas de "+transferirDe+" para "+transferirPara, "Transferencia", "Finalizado", Double.parseDouble(montante), contaPoupanca.getSaldoConta(), contaPoupanca);
            transacaoContaPoupancaDao.save(transacaoContaPoupanca);
        } else {
            throw new Exception("Transferencia Inválida");
        }
    }
    
    public List<Recebedor> procurarListaRecebedor(Principal principal) {
        String username = principal.getName();
        List<Recebedor> listaRecebedor = recebedorDao.findAll().stream() 			
                .filter(recebedor -> username.equals(recebedor.getUsuario().getUsername()))	
                .collect(Collectors.toList());

        return listaRecebedor;
    }

    public Recebedor salvarRecebedor(Recebedor recebedor) {
        return recebedorDao.save(recebedor);
    }

    public Recebedor procurarPorNomeRecebedor(String nomeRecebedor) {
        return recebedorDao.findByName(nomeRecebedor);
    }

    public void deletarPorNomeRecebedor(String nomeRecebedor) {
        recebedorDao.deleteByName(nomeRecebedor);
    }
    
    public void transferirParaOutraPessoa(Recebedor recebedor, String tipoConta, String montante, ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) {
        if (tipoConta.equalsIgnoreCase("Corrente")) {
            contaCorrente.setSaldoConta(contaCorrente.getSaldoConta().subtract(new BigDecimal(montante)));
            contaCorrenteDao.save(contaCorrente);

            Date data = new Date();

            TransacaoContaCorrente transacaoContaCorrente = new TransacaoContaCorrente(data, "Transferir para Recebedor "+recebedor.getName(), "Transferencia", "Finalizado", Double.parseDouble(montante), contaCorrente.getSaldoConta(), contaCorrente);
            transacaoContaCorrenteDao.save(transacaoContaCorrente);
        } else if (tipoConta.equalsIgnoreCase("Poupanca")) {
            contaPoupanca.setSaldoConta(contaPoupanca.getSaldoConta().subtract(new BigDecimal(montante)));
            contaPoupancaDao.save(contaPoupanca);

            Date data = new Date();

            TransacaoContaPoupanca transacaoContaPoupanca = new TransacaoContaPoupanca(data, "Transferir para Recedebor "+recebedor.getName(), "Transferencia", "Finalizado", Double.parseDouble(montante), contaPoupanca.getSaldoConta(), contaPoupanca);
            transacaoContaPoupancaDao.save(transacaoContaPoupanca);
        }
    }
}
