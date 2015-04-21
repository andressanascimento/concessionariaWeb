package br.gov.sp.fatec.dao;

import java.util.ArrayList;
import java.util.Collection;

import br.gov.sp.fatec.model.Carro;
import br.gov.sp.fatec.vo.CarroVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class CarroDaoImpl extends DaoGenericoImpl<Carro, Long> implements CarroDao {
	
	@Override
	public CarroVO paraCarroVO(Carro entidade) {
		return new CarroVO(entidade.getId(), entidade.getPlaca(), entidade.getModelo(), entidade.getMarca());
	}
	
	@Override
	public Collection<CarroVO> paraColecaoCarroVO(Collection<Carro> entidades) {
		Collection<CarroVO> resultado = new ArrayList<CarroVO>();
		if(entidades != null) {
			for(Carro entidade: entidades) {
				resultado.add(paraCarroVO(entidade));
			}
		}
		return resultado;
	}

	@Override
	public Carro paraEntidade(CarroVO carroVO) {
		Carro carro = new Carro();
		carro.setId(carroVO.getId());
		carro.setPlaca(carroVO.getPlaca());
		carro.setModelo(carroVO.getModelo());
		carro.setMarca(carroVO.getMarca());
		return carro;
	}
        
        @SuppressWarnings("unchecked")
	@Override
	public Collection<CarroVO> pesquisa(CarroVO carroVO) {
		Criteria criteria = this.getCurrentSession().createCriteria(Carro.class);
		if(carroVO.getPlaca() != null && !carroVO.getPlaca().isEmpty()) {
			criteria.add(Restrictions.ilike("placa", carroVO.getPlaca(), MatchMode.ANYWHERE));
		}
		return paraColecaoCarroVO(criteria.list());
	}
}
