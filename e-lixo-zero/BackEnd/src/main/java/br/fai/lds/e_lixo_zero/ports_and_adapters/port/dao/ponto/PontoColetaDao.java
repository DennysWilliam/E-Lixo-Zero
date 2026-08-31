package br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.ponto;

import br.fai.lds.e_lixo_zero.domain.PontoColetaModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.crud.CrudDao;

import java.util.List;

public interface PontoColetaDao extends CrudDao<PontoColetaModel> {
    List<PontoColetaModel> readByCidade(final String cidade);
}
