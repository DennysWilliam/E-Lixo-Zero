package br.fai.e.lixo.zero.ports_and_adapters.port.service.usuario;

import br.fai.e.lixo.zero.domain.UsuarioModel;

public interface FindByEmailService {

    UsuarioModel findByEmail(final String email);
}
