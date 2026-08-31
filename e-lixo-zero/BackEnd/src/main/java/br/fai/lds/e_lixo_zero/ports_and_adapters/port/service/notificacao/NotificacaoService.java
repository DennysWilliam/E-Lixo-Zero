package br.fai.lds.e_lixo_zero.ports_and_adapters.port.service.notificacao;

import br.fai.lds.e_lixo_zero.domain.NotificacaoModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.service.crud.CrudService;

import java.util.List;

public interface NotificacaoService extends CrudService<NotificacaoModel> {
    List<NotificacaoModel> findByUsuarioId(final int usuarioId);
    List<NotificacaoModel> findNaoLidasByUsuarioId(final int usuarioId);
    boolean marcarComoLida(final int id);
}
