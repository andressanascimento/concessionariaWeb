package br.gov.sp.fatec.service;

import java.util.Collection;

import br.gov.sp.fatec.vo.CarroVO;

public interface CarroBO {
	

	public void salvarCarro(CarroVO carro);
	

	public void removerCarro(Long id);
	

	public Collection<CarroVO> listarCarros();

        
	public Collection<CarroVO> pesquisarCarros(CarroVO carroVO);
	

	public CarroVO recuperarCarro(Long id);

}
