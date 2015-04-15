/**
 * 
 */
package br.gov.sp.fatec.web.controller;

import java.util.ArrayList;
import java.util.Collection;

import br.gov.sp.fatec.service.ClienteBO;
import br.gov.sp.fatec.vo.ClienteVO;
import br.gov.sp.fatec.web.WebUtils;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean(name="clienteController")
@SessionScoped
public class ClienteController implements Serializable{
	
	private ClienteVO pesquisa;
	private ClienteVO edicao;
	private Collection<ClienteVO> resultado;
        @Autowired
        @ManagedProperty(value="#{clienteBO}")
	private ClienteBO clienteBO;
        
        
	/**
	 * @param clienteBO the clienteBO to set
	 */
	public void setClienteBO(ClienteBO clienteBO) {
		this.clienteBO = clienteBO;
	}
	
	public void inicializar() {
		if(getPesquisa() == null) {
			setPesquisa(new ClienteVO());
			setEdicao(new ClienteVO());
			setResultado(new ArrayList<ClienteVO>());
			pesquisar();
		}
	}
	
	public String novo() {
		setEdicao(new ClienteVO());
		return "/cliente/clienteEdicao";
	}
	
	public void pesquisar() {
		setResultado(clienteBO.pesquisarClientes(getPesquisa()));
	}
	
	public String cancelar() {
		return "/cliente/cliente";
	}
	
	public String salvar() {
		try{
			clienteBO.salvarCliente(getEdicao());
			pesquisar();
			WebUtils.incluirMensagemInfo("Cliente gravado com sucesso!");
			return "/cliente/cliente";
		}
		catch(Throwable t) {
			WebUtils.incluirMensagemErro(WebUtils.recuperarCausa(t));
		}
		return null;
	}
	
	public void remover(Long id) {
		clienteBO.removerCliente(id);
		pesquisar();
	}
	
	public String editar(Long id) {
		setEdicao(clienteBO.recuperarCliente(id));
		return "/cliente/clienteEdicao";
	}

	/**
	 * @return the pesquisa
	 */
	public ClienteVO getPesquisa() {
		return pesquisa;
	}

	/**
	 * @param pesquisa the pesquisa to set
	 */
	public void setPesquisa(ClienteVO pesquisa) {
		this.pesquisa = pesquisa;
	}

	/**
	 * @return the edicao
	 */
	public ClienteVO getEdicao() {
		return edicao;
	}

	/**
	 * @param edicao the edicao to set
	 */
	public void setEdicao(ClienteVO edicao) {
		this.edicao = edicao;
	}
	
	/**
	 * @return the resultado
	 */
	public Collection<ClienteVO> getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(Collection<ClienteVO> resultado) {
		this.resultado = resultado;
	}
	
}
