package br.gov.sp.fatec.dao;

import java.util.Collection;

import br.gov.sp.fatec.model.Aluguel;
import br.gov.sp.fatec.vo.AluguelVO;

public interface AluguelDao extends DaoGenerico<Aluguel, Long>{
	/**
	 * Converte entidade para VO
	 * @param entidade Entidade
	 * @return VO
	 */
	public AluguelVO paraAluguelVO(Aluguel entidade);

	/**
	 * Converte uma colecao de entidades para colecao de VOs
	 * @param entidades Colecao de entidades
	 * @return Colecao de VOs
	 */
	public Collection<AluguelVO> paraColecaoAluguelVO(Collection<Aluguel> entidades);
	
	/**
	 * Converte um VO para uma entidade
	 * @param estoqueVO VO
	 * @return Entidade
	 */
	public Aluguel paraEntidade(AluguelVO estoqueVO);
	
	public Collection<AluguelVO> pesquisa(AluguelVO aluguelVO);
        
        public Collection<AluguelVO> listarRecentes();
}
