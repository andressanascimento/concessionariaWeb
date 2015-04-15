package br.gov.sp.fatec.service;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.dao.ClienteDao;
import br.gov.sp.fatec.model.Cliente;
import br.gov.sp.fatec.vo.ClienteVO;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public class ClienteBOImpl implements ClienteBO, Serializable {

	private static final long serialVersionUID = 5471811505341189578L;
	
	private ClienteDao clienteDao;
	
	public void setPessoaDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	@Override
	public void salvarCliente(ClienteVO clienteVO) {
		Cliente cliente = clienteDao.paraEntidade(clienteVO);
                Long id = cliente.getId();
                if(id != null){
                    clienteDao.atualizar(cliente);
                }else{
                    clienteDao.salvar(cliente);
                }
		
	}
	
	@Override
	public void removerCliente(Long id) {
		Cliente cliente = clienteDao.pesquisarPorId(id);
		if(cliente == null) {
			throw new RuntimeException("Nao existe pessoa com ID: " + id);
		}
		clienteDao.excluir(cliente);
	}

	@Override
	public Collection<ClienteVO> listarClientes() {
		return clienteDao.paraColecaoClienteVO(clienteDao.todos());
	}

	@Override
	public Collection<ClienteVO> pesquisarClientes(ClienteVO clienteVO) {
		return clienteDao.pesquisa(clienteVO);
	}

	@Override
	public ClienteVO recuperarCliente(Long id) {
		Cliente cliente = clienteDao.pesquisarPorId(id);
		if(cliente == null) {
			throw new RuntimeException("N�o existe cliente com ID: " + id);
		}
		return clienteDao.paraClienteVO(cliente);
	}
        

}
