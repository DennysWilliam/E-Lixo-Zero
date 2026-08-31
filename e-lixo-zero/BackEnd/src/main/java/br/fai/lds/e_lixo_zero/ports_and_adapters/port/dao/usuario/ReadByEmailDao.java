package br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.usuario;

import br.fai.lds.e_lixo_zero.domain.UsuarioModel;

public interface ReadByEmailDao {

    UsuarioModel readByEmail(final String email);
}
