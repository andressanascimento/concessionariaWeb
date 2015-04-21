package br.gov.sp.fatec.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.gov.sp.fatec.model.Aluguel;
import br.gov.sp.fatec.vo.AluguelVO;
import br.gov.sp.fatec.vo.CarroVO;
import java.util.List;
import org.hibernate.criterion.Order;

public class AluguelDaoImpl extends DaoGenericoImpl<Aluguel, Long> implements AluguelDao {

    private CarroDao carroDao;
    private ClienteDao clienteDao;

    public void setCarroDao(CarroDao carroDao) {
        this.carroDao = carroDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public AluguelVO paraAluguelVO(Aluguel entidade) {
        AluguelVO aluguelVO = new AluguelVO();
        aluguelVO.setId(entidade.getId());
        aluguelVO.setCliente(clienteDao.paraClienteVO(entidade.getCliente()));
//		aluguelVO.setCarros(carroDao.paraColecaoCarroVO(entidade.getCarros()));
        return aluguelVO;
    }

    @Override
    public Collection<AluguelVO> paraColecaoAluguelVO(Collection<Aluguel> entidades) {
        Collection<AluguelVO> resultado = new ArrayList<AluguelVO>();
        if (entidades != null) {
            for (Aluguel entidade : entidades) {
                resultado.add(paraAluguelVO(entidade));
            }
        }
        return resultado;
    }

    @Override
    public Aluguel paraEntidade(AluguelVO aluguelVO) {
        Aluguel aluguel = null;
        if (aluguelVO.getId() != null) {
            aluguel = pesquisarPorId(aluguelVO.getId());
        }
        if (aluguel == null) {
            aluguel = new Aluguel();
        }
        aluguel.setId(aluguelVO.getId());
        aluguel.setCliente(clienteDao.paraEntidade(aluguelVO.getCliente()));
        if (aluguelVO.getCarros() != null) {
            for (CarroVO carroVO : aluguelVO.getCarros()) {
//				aluguel.getCarros().add(carroDao.paraEntidade(carroVO));
            }
        }
        return aluguel;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<AluguelVO> pesquisa(AluguelVO aluguelVO) {
        Criteria criteria = this.getCurrentSession().createCriteria(Aluguel.class);
        if (aluguelVO.getId() != null) {
            criteria.add(Restrictions.idEq(aluguelVO.getId()));
        }

        return paraColecaoAluguelVO(criteria.list());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<AluguelVO> listarRecentes() {
        Criteria criteria = this.getCurrentSession().createCriteria(Aluguel.class);
        criteria.addOrder(Order.desc("id"));
        criteria.setMaxResults(10);

        return paraColecaoAluguelVO(criteria.list());
    }

}
