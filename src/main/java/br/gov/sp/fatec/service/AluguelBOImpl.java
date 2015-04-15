package br.gov.sp.fatec.service;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.dao.AluguelDao;
import br.gov.sp.fatec.dao.CarroDao;
import br.gov.sp.fatec.dao.ClienteDao;
import br.gov.sp.fatec.model.Aluguel;
import br.gov.sp.fatec.model.Carro;
import br.gov.sp.fatec.model.Cliente;
import br.gov.sp.fatec.vo.AluguelVO;
import br.gov.sp.fatec.vo.CarroVO;
import br.gov.sp.fatec.vo.ClienteVO;

@Transactional
public class AluguelBOImpl implements AluguelBO, Serializable{

	private static final long serialVersionUID = 5471811505341189578L;
	
	private AluguelDao aluguelDao;
	private CarroDao carroDao;
	private ClienteDao clienteDao;
	
	public void setAluguelDao(AluguelDao aluguelDao) {
		this.aluguelDao = aluguelDao;
	}
	
	public void setCarroDao(CarroDao itemDao) {
		this.carroDao = itemDao;
	}
	
	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	@Override
	public void criarAluguel(AluguelVO aluguelVO) {
		Aluguel aluguel = aluguelDao.salvar(aluguelDao.paraEntidade(aluguelVO));
	}
	

	@Override
	public void removerAluguel(Long id) {
		Aluguel stock = aluguelDao.pesquisarPorId(id);
		if(stock == null) {
			throw new RuntimeException("Nao existe estoque com ID: " + id);
		}
		aluguelDao.excluir(stock);
	}

	
	@Override
	public Collection<AluguelVO> pesquisarAluguel(AluguelVO aluguelVO) {
		return aluguelDao.pesquisa(aluguelVO);
	}

	@Override
	public AluguelVO recuperarAluguel(Long id) {
		Aluguel stock = aluguelDao.pesquisarPorId(id);
		if(stock == null) {
			throw new RuntimeException("Não existe aluguel com ID: " + id);
		}
		return aluguelDao.paraAluguelVO(stock);
	}
	
	@Override
	public Collection<AluguelVO> listarAluguel() {
		return aluguelDao.paraColecaoAluguelVO(aluguelDao.todos());
	}

	@Override
	public void criarCliente(String nome, String email, String telefone) {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setEmail(telefone);
		clienteDao.salvar(cliente);
	}
	
	@Override
	public void removerCliente(Long id) {
		Cliente cliente = clienteDao.pesquisarPorId(id);
		if(cliente == null) {
			throw new RuntimeException("Nao existe cliente com ID: " + id);
		}
		clienteDao.excluir(cliente);
	}

	@Override
	public Collection<ClienteVO> listarPessoas() {
		return clienteDao.paraColecaoClienteVO(clienteDao.todos());
	}

	@Override
	public void rollbackTest(AluguelVO aluguelVO) {
		aluguelDao.salvar(aluguelDao.paraEntidade(aluguelVO));
		throw new RuntimeException("Teste");
	}

}
