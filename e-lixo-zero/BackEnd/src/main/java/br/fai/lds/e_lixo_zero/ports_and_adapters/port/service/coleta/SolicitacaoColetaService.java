package br.fai.lds.e_lixo_zero.ports_and_adapters.port.service.coleta;

import br.fai.lds.e_lixo_zero.domain.SolicitacaoColetaModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.service.crud.CrudService;

import java.util.List;

public interface SolicitacaoColetaService extends CrudService<SolicitacaoColetaModel> {
    List<SolicitacaoColetaModel> findByUsuarioId(final int usuarioId);
    boolean updateStatus(final int id, final String status);
}
