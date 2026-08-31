package br.fai.e.lixo.zero.ports_and_adapters.port.dao.usuario;

import br.fai.e.lixo.zero.domain.UsuarioModel;
import br.fai.e.lixo.zero.ports_and_adapters.port.dao.crud.CrudDao;

public interface UsuarioDao extends CrudDao<UsuarioModel>, ReadByEmailDao {
}
