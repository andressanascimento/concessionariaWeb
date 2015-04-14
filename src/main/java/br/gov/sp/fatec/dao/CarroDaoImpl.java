package br.gov.sp.fatec.dao;

import java.util.ArrayList;
import java.util.Collection;

import br.gov.sp.fatec.model.Carro;
import br.gov.sp.fatec.vo.CarroVO;

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

}
