package br.gov.sp.fatec.dao;

import java.util.Collection;

import br.gov.sp.fatec.model.Carro;
import br.gov.sp.fatec.vo.CarroVO;

public interface CarroDao extends DaoGenerico<Carro, Long> {
	/**
	 * Converte entidade para VO
	 * @param entidade Entidade
	 * @return VO
	 */
	public CarroVO paraCarroVO(Carro entidade);
	
	/**
	 * Converte uma colecao de entidades para colecao de VOs
	 * @param entidades Colecao de entidades
	 * @return Colecao de VOs
	 */
	public Collection<CarroVO> paraColecaoCarroVO(Collection<Carro> entidades);
	
	/**
	 * Converte um VO para uma entidade
	 * @param itemVO VO
	 * @return Entidade
	 */
	public Carro paraEntidade(CarroVO carroVO);
}
