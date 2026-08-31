package br.fai.e.lixo.zero.ports_and_adapters.port.service.usuario;

import br.fai.e.lixo.zero.domain.UsuarioModel;
import br.fai.e.lixo.zero.ports_and_adapters.port.service.crud.CrudService;

public interface UsuarioService extends CrudService<UsuarioModel>, FindByEmailService {
}
