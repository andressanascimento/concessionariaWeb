package br.gov.sp.fatec.vo;

import java.io.Serializable;

public class CarroVO implements Serializable{

	private Long id;
	
	private String placa;
	
	private String modelo;
	 
	private String marca;
	
	public CarroVO(Long id, String placa, String modelo, String marca) {
		this.id = id;
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
	}

	public CarroVO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
}
