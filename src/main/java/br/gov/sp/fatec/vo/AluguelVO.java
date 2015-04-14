package br.gov.sp.fatec.vo;

import java.util.Collection;

import br.gov.sp.fatec.vo.ClienteVO;

public class AluguelVO {

	private Long id;

	private ClienteVO cliente;
	
	private Collection<CarroVO> carros;
	
	public AluguelVO(Long id, ClienteVO cliente, Collection<CarroVO> carros){
		this.setId(id);
		this.setCliente(cliente);
		this.setCarros(carros);
	}

	public AluguelVO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public Collection<CarroVO> getCarros() {
		return carros;
	}

	public void setCarros(Collection<CarroVO> carros) {
		this.carros = carros;
	}
	
}
