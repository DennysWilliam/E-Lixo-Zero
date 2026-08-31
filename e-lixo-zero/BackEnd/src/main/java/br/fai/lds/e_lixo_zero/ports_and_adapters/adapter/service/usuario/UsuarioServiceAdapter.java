package br.fai.lds.e_lixo_zero.ports_and_adapters.adapter.service.usuario;

import br.fai.lds.e_lixo_zero.domain.UsuarioModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.usuario.UsuarioDao;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceAdapter implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public int create(final UsuarioModel usuarioModel) {
        if (usuarioModel == null) {
            return 0;
        }
        if (isInvalidString(usuarioModel.getNomeCompleto())) {
            return 0;
        }
        if (isInvalidEmail(usuarioModel.getEmail())) {
            return 0;
        }
        if (isInvalidString(usuarioModel.getSenha())) {
            return 0;
        }
        if (findByEmail(usuarioModel.getEmail()) != null) {
            return 0;
        }
        return usuarioDao.add(usuarioModel);
    }

    @Override
    public void delete(final int id) {
        if (id <= 0) {
            return;
        }
        usuarioDao.remove(id);
    }

    @Override
    public boolean update(final int id, final UsuarioModel usuarioModel) {
        final UsuarioModel stored = findById(id);
        if (stored == null || usuarioModel == null) {
            return false;
        }
        usuarioDao.updateInformation(id, usuarioModel);
        return true;
    }

    @Override
    public UsuarioModel findById(final int id) {
        if (id <= 0) {
            return null;
        }
        return usuarioDao.readyById(id);
    }

    @Override
    public List<UsuarioModel> findALl() {
        return usuarioDao.readAll();
    }

    @Override
    public UsuarioModel findByEmail(final String email) {
        if (isInvalidEmail(email)) {
            return null;
        }
        return usuarioDao.readByEmail(email);
    }

    private boolean isInvalidEmail(final String email) {
        return email == null || email.isBlank() || !email.contains("@");
    }

    private boolean isInvalidString(final String value) {
        return value == null || value.isBlank();
    }
}
