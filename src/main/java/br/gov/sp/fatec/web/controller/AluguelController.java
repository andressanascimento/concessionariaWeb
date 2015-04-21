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
import java.io.Serializable;
import br.gov.sp.fatec.service.CarroBO;
import br.gov.sp.fatec.service.ClienteBO;
import br.gov.sp.fatec.vo.ClienteVO;

@ManagedBean(name = "aluguelController")
@SessionScoped
public class AluguelController implements Serializable {

    private Boolean editar = false;
    private AluguelVO pesquisa;
    private AluguelVO edicao;
    private String item;
    private Collection<AluguelVO> resultado;
     private Collection<AluguelVO> recentes;
    @ManagedProperty(value = "#{aluguelBO}")
    private AluguelBO aluguelBO;

    @ManagedProperty(value = "#{carroBO}")
    private CarroBO carroBO;

    @ManagedProperty(value = "#{clienteBO}")
    private ClienteBO clienteBO;

    private Collection<CarroVO> carros;
    private Collection<ClienteVO> clientes;

    public void setAluguelBO(AluguelBO aluguelBO) {
        this.aluguelBO = aluguelBO;
    }

    public void inicializar() {
        if (getPesquisa() == null) {
            setPesquisa(new AluguelVO());
            setEdicao(new AluguelVO());
            setResultado(new ArrayList<AluguelVO>());
            pesquisar();
            setEditar(false);
        }

        setRecentes(aluguelBO.listarRecentes());
        
        if (this.carros == null) {
            setCarros(carroBO.listarCarros());
        }

        if (this.clientes == null) {
            setClientes(clienteBO.listarClientes());
        }
    }

    public void novo() {
        setEdicao(new AluguelVO());
        setEditar(true);
    }

    public void pesquisar() {
        setResultado(aluguelBO.pesquisarAluguel(getPesquisa()));
    }
    
    public void listarRecentes() {
        setRecentes(aluguelBO.listarRecentes());
    }

    public void cancelar() {
        setEditar(false);
    }

    public void salvar() {
        try {
            aluguelBO.criarAluguel(getEdicao());
            pesquisar();
            setEditar(false);
            WebUtils.incluirMensagemInfo("Estoque gravado com sucesso!");
        } catch (Throwable t) {
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
        if (getCarro() == null || getCarro().isEmpty()) {
            WebUtils.incluirMensagemErro("É necessário informar uma marca para o carro!");
        } else {
            CarroVO carro = new CarroVO();
            carro.setMarca(getCarro());
            getEdicao().getCarros().add(carro);
            setCarro(null);
        }
    }

    public void removerCarro(Long id, String nome) {
        // Quando é nulo vem 0
        if (id.equals(0l)) {
            id = null;
        }
        for (CarroVO carro : getEdicao().getCarros()) {
            if (((carro.getId() == null && id == null) || (carro.getId() != null && carro.getId().equals(id))) && carro.getMarca().equals(nome)) {
                getEdicao().getCarros().remove(carro);
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public CarroBO getCarroBO() {
        return carroBO;
    }

    public void setCarroBO(CarroBO carroBO) {
        this.carroBO = carroBO;
    }

    public ClienteBO getClienteBO() {
        return clienteBO;
    }

    public void setClienteBO(ClienteBO clienteBO) {
        this.clienteBO = clienteBO;
    }

    public Collection<CarroVO> getCarros() {
        return carros;
    }

    public void setCarros(Collection<CarroVO> carros) {
        this.carros = carros;
    }

    public Collection<ClienteVO> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<ClienteVO> clientes) {
        this.clientes = clientes;
    }

    public Collection<AluguelVO> getRecentes() {
        return recentes;
    }

    public void setRecentes(Collection<AluguelVO> recentes) {
        this.recentes = recentes;
    }
        
}
