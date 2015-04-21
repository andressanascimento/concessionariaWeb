package br.gov.sp.fatec.web.controller;

import java.util.ArrayList;
import java.util.Collection;

import br.gov.sp.fatec.service.CarroBO;
import br.gov.sp.fatec.vo.CarroVO;
import br.gov.sp.fatec.web.WebUtils;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean(name = "carroController")
@SessionScoped
public class CarroController implements Serializable {

    private CarroVO pesquisa;
    private CarroVO edicao;
    private Collection<CarroVO> resultado;
    @Autowired
    @ManagedProperty(value = "#{carroBO}")
    private CarroBO carroBO;

    public void setClienteBO(CarroBO carroBO) {
        this.carroBO = carroBO;
    }

    public void inicializar() {
        if (getPesquisa() == null) {
            setPesquisa(new CarroVO());
            setEdicao(new CarroVO());
            setResultado(new ArrayList<CarroVO>());
            pesquisar();
        }
    }

    public String novo() {
        setEdicao(new CarroVO());
        return "/carro/carroEdicao";
    }

    public void pesquisar() {
        setResultado(carroBO.pesquisarCarros(getPesquisa()));
    }

    public String cancelar() {
        return "/carro/carro";
    }

    public String salvar() {
        try {
            carroBO.salvarCarro(getEdicao());
            pesquisar();
            WebUtils.incluirMensagemInfo("Carro gravado com sucesso!");
            return "/carro/carro";
        } catch (Throwable t) {
            WebUtils.incluirMensagemErro(WebUtils.recuperarCausa(t));
        }
        return null;
    }

    public void remover(Long id) {
        carroBO.removerCarro(id);
        pesquisar();
    }

    public String editar(Long id) {
        setEdicao(carroBO.recuperarCarro(id));
        return "/carro/carroEdicao";
    }

    public CarroVO getPesquisa() {
        return pesquisa;
    }

    /**
     * @param pesquisa the pesquisa to set
     */
    public void setPesquisa(CarroVO pesquisa) {
        this.pesquisa = pesquisa;
    }

    /**
     * @return the edicao
     */
    public CarroVO getEdicao() {
        return edicao;
    }

    /**
     * @param edicao the edicao to set
     */
    public void setEdicao(CarroVO edicao) {
        this.edicao = edicao;
    }

    /**
     * @return the resultado
     */
    public Collection<CarroVO> getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(Collection<CarroVO> resultado) {
        this.resultado = resultado;
    }

    public CarroBO getCarroBO() {
        return carroBO;
    }

    public void setCarroBO(CarroBO carroBO) {
        this.carroBO = carroBO;
    }

}
