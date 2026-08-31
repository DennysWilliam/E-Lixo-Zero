package br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.coleta;

import br.fai.lds.e_lixo_zero.domain.SolicitacaoColetaModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.crud.CrudDao;

import java.util.List;

public interface SolicitacaoColetaDao extends CrudDao<SolicitacaoColetaModel> {
    List<SolicitacaoColetaModel> readByUsuarioId(final int usuarioId);
    void updateStatus(final int id, final String status);
}
