package br.gov.sp.fatec.service;

import java.util.Collection;

import br.gov.sp.fatec.vo.ClienteVO;

public interface ClienteBO {
	

	public void salvarCliente(ClienteVO cliente);
	

	public void removerCliente(Long id);
	

	public Collection<ClienteVO> listarClientes();

        
	public Collection<ClienteVO> pesquisarClientes(ClienteVO clienteVO);
	

	public ClienteVO recuperarCliente(Long id);

}
