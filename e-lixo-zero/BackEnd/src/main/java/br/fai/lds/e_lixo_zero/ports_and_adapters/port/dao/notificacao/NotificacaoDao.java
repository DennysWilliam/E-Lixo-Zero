package br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.notificacao;

import br.fai.lds.e_lixo_zero.domain.NotificacaoModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.crud.CrudDao;

import java.util.List;

public interface NotificacaoDao extends CrudDao<NotificacaoModel> {
    List<NotificacaoModel> readByUsuarioId(final int usuarioId);
    List<NotificacaoModel> readNaoLidasByUsuarioId(final int usuarioId);
    void marcarComoLida(final int id);
}
