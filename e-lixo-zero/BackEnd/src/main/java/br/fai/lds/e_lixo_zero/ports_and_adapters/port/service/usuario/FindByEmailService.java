package br.fai.lds.e_lixo_zero.ports_and_adapters.port.service.usuario;

import br.fai.lds.e_lixo_zero.domain.UsuarioModel;

public interface FindByEmailService {

    UsuarioModel findByEmail(final String email);
}
