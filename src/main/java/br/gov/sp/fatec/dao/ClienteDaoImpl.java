package br.gov.sp.fatec.dao;

import java.util.ArrayList;
import java.util.Collection;

import br.gov.sp.fatec.model.Cliente;
import br.gov.sp.fatec.vo.ClienteVO;
import br.gov.sp.fatec.dao.ClienteDao;

public class ClienteDaoImpl extends DaoGenericoImpl<Cliente, Long> implements ClienteDao {
	
	@Override
	public ClienteVO paraClienteVO(Cliente entidade) {
		return new ClienteVO(entidade.getId(), entidade.getNome(), entidade.getEmail(),entidade.getTelefone());
	}
	
	@Override
	public Collection<ClienteVO> paraColecaoClienteVO(Collection<Cliente> entidades) {
		Collection<ClienteVO> resultado = new ArrayList<ClienteVO>();
		if(entidades != null) {
			for(Cliente entidade: entidades) {
				resultado.add(paraClienteVO(entidade));
			}
		}
		return resultado;
	}

	@Override
	public Cliente paraEntidade(ClienteVO clienteVO) {
		Cliente cliente = new Cliente();
		cliente.setId(clienteVO.getId());
		cliente.setNome(clienteVO.getNome());
		cliente.setEmail(clienteVO.getEmail());
		cliente.setTelefone(clienteVO.getTelefone());
		return cliente;
	}

}
