package br.gov.sp.fatec.dao;

import java.util.Collection;
import br.gov.sp.fatec.model.Cliente;
import br.gov.sp.fatec.vo.ClienteVO;

public interface ClienteDao extends DaoGenerico<Cliente, Long> {

	/**
	 * Converte entidade para VO
	 * @param entidade Entidade
	 * @return VO
	 */
	public ClienteVO paraClienteVO(Cliente entidade);
	
	/**
	 * Converte uma colecao de entidades para colecao de VOs
	 * @param entidades Colecao de entidades
	 * @return Colecao de VOs
	 */
	public Collection<ClienteVO> paraColecaoClienteVO(Collection<Cliente> entidades);
	
	/**
	 * Converte um VO para uma entidade
	 * @param pessoaVO VO
	 * @return Entidade
	 */
	public Cliente paraEntidade(ClienteVO clienteVO);
        
        public Collection<ClienteVO> pesquisa(ClienteVO clienteVO);
	
}
