package br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.usuario;

import br.fai.lds.e_lixo_zero.domain.UsuarioModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.crud.CrudDao;

public interface UsuarioDao extends CrudDao<UsuarioModel>, ReadByEmailDao {
}
