package br.fai.e.lixo.zero.ports_and_adapters.port.dao.usuario;

import br.fai.e.lixo.zero.domain.UsuarioModel;

public interface ReadByEmailDao {

    UsuarioModel readByEmail(final String email);
}
