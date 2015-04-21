package br.gov.sp.fatec.service;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.dao.CarroDao;
import br.gov.sp.fatec.model.Carro;
import br.gov.sp.fatec.vo.CarroVO;

@Transactional
public class CarroBOImpl implements CarroBO, Serializable {

    private static final long serialVersionUID = 5471811505341189578L;

    private CarroDao carroDao;

    public void setCarroDao(CarroDao carroDao) {
        this.carroDao = carroDao;
    }

    @Override
    public void salvarCarro(CarroVO carroVO) {
        Carro carro = carroDao.paraEntidade(carroVO);
        Long id = carro.getId();
        if (id != null) {
            carroDao.atualizar(carro);
        } else {
            carroDao.salvar(carro);
        }

    }

    @Override
    public void removerCarro(Long id) {
        Carro carro = carroDao.pesquisarPorId(id);
        if (carro == null) {
            throw new RuntimeException("Nao existe carro com ID: " + id);
        }
        carroDao.excluir(carro);
    }

    @Override
    public Collection<CarroVO> listarCarros() {
        return carroDao.paraColecaoCarroVO(carroDao.todos());
    }

    @Override
    public Collection<CarroVO> pesquisarCarros(CarroVO carroVO) {
        return carroDao.pesquisa(carroVO);
    }

    @Override
    public CarroVO recuperarCarro(Long id) {
        Carro carro = carroDao.pesquisarPorId(id);
        if (carro == null) {
            throw new RuntimeException("Não existe carro com ID: " + id);
        }
        return carroDao.paraCarroVO(carro);
    }

    public CarroDao getCarroDao() {
        return carroDao;
    }

}
