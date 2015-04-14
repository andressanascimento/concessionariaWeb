package br.gov.sp.fatec.service;

import java.util.Collection;

import br.gov.sp.fatec.vo.AluguelVO;
import br.gov.sp.fatec.vo.ClienteVO;

public interface AluguelBO {

	/**
	 * Cria um estoque
	 * @param estoqueVO Estoque
	 */
	public void criarAluguel(AluguelVO aluguelVO);
	
	/**
	 * Remove um estoque
	 * @param id Id do estoque
	 */
	public void removerAluguel(Long id);
	
	/**
	 * Lista todos os estoques
	 * @return Todos os estoques
	 */
	public Collection<AluguelVO> listarAluguel();
	
	/**
	 * Cria uma pessoa
	 * @param nome Nome
	 */
	public void criarCliente(String nome, String email, String telefone);
	
	/**
	 * Remove uma pessoa
	 * @param id Id da pessoa
	 */
	public void removerCliente(Long id);
	
	/**
	 * Lista todas as pessoas
	 * @return Todas as pessoas
	 */
	public Collection<ClienteVO> listarPessoas();	
	
	/**
	 * Teste de rollback
	 * @param estoqueVO
	 */
	public void rollbackTest(AluguelVO estoqueVO);
	
	public Collection<AluguelVO> pesquisarAluguel(AluguelVO aluguelVO);
	
	public AluguelVO recuperarAluguel(Long id);
	

}
