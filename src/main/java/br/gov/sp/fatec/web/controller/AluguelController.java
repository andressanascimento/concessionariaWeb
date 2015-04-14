/**
 * 
 */
package br.gov.sp.fatec.web.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.gov.sp.fatec.service.AluguelBO;
import br.gov.sp.fatec.vo.AluguelVO;
import br.gov.sp.fatec.vo.CarroVO;
import br.gov.sp.fatec.web.WebUtils;


@ManagedBean(name="aluguelController")
@SessionScoped
public class AluguelController {
	
	private Boolean editar = false;
	private AluguelVO pesquisa;
	private AluguelVO edicao;
	private String item;
	private Collection<AluguelVO> resultado;
	@ManagedProperty(value="#{aluguelBO}")
	private AluguelBO aluguelBO;

	/**
	 * @param estoqueBO the estoqueBO to set
	 */
	public void setAluguelBO(AluguelBO estoqueBO) {
		this.aluguelBO = estoqueBO;
	}
	
	public void inicializar() {
		if(getPesquisa() == null) {
			setPesquisa(new AluguelVO());
			setEdicao(new AluguelVO());
			setResultado(new ArrayList<AluguelVO>());
			pesquisar();
			setEditar(false);
		}
	}
	
	public void novo() {
		setEdicao(new AluguelVO());
		setEditar(true);
	}
	
	public void pesquisar() {
		setResultado(aluguelBO.pesquisarAluguel(getPesquisa()));
	}
	
	public void cancelar() {
		setEditar(false);
	}
	
	public void salvar() {
		try{
			aluguelBO.criarAluguel(getEdicao());
			pesquisar();
			setEditar(false);
			WebUtils.incluirMensagemInfo("Estoque gravado com sucesso!");
		}
		catch(Throwable t) {
			WebUtils.incluirMensagemErro(WebUtils.recuperarCausa(t));
		}
	}
	
	public void remover(Long id) {
		aluguelBO.removerAluguel(id);
		pesquisar();
	}
	
	public void editar(Long id) {
		setEdicao(aluguelBO.recuperarAluguel(id));
		setEditar(true);
	}
	
	public void incluirCarro() {
		if(getCarro() == null || getCarro().isEmpty()) {
			WebUtils.incluirMensagemErro("É necessário informar uma marca para o carro!");
		}
		else {
			CarroVO item = new CarroVO();
			item.setMarca(getCarro());
			getEdicao().getCarros().add(item);
			setCarro(null);
		}
	}
	
	public void removerCarro(Long id, String nome) {
		// Quando é nulo vem 0
		if(id.equals(0l)) {
			id = null;
		}
		for(CarroVO item: getEdicao().getCarros()) {
			if(((item.getId() == null && id == null) || (item.getId() != null && item.getId().equals(id))) && item.getMarca().equals(nome)) {
				getEdicao().getCarros().remove(item);
				break;
			}
		}
	}

	/**
	 * @return the editar
	 */
	public Boolean getEditar() {
		return editar;
	}

	/**
	 * @param editar the editar to set
	 */
	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	/**
	 * @return the pesquisa
	 */
	public AluguelVO getPesquisa() {
		return pesquisa;
	}

	/**
	 * @param pesquisa the pesquisa to set
	 */
	public void setPesquisa(AluguelVO pesquisa) {
		this.pesquisa = pesquisa;
	}

	/**
	 * @return the edicao
	 */
	public AluguelVO getEdicao() {
		return edicao;
	}

	/**
	 * @param edicao the edicao to set
	 */
	public void setEdicao(AluguelVO edicao) {
		this.edicao = edicao;
	}

	/**
	 * @return the item
	 */
	public String getCarro() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setCarro(String item) {
		this.item = item;
	}
	
	/**
	 * @return the resultado
	 */
	public Collection<AluguelVO> getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(Collection<AluguelVO> resultado) {
		this.resultado = resultado;
	}
	
}
