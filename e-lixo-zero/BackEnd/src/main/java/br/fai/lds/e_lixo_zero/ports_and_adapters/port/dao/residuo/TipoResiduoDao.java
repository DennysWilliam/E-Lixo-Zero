package br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.residuo;

import br.fai.lds.e_lixo_zero.domain.TipoResiduoModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.crud.CrudDao;

public interface TipoResiduoDao extends CrudDao<TipoResiduoModel> {
    TipoResiduoModel readByNome(final String nome);
}
