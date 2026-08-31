package br.fai.lds.e_lixo_zero.ports_and_adapters.adapter.dao.usuario;

import br.fai.lds.e_lixo_zero.domain.UsuarioModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.usuario.UsuarioDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class UsuarioPostgresDaoAdapter implements UsuarioDao {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioPostgresDaoAdapter(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int add(final UsuarioModel entity) {
        final String sql = "INSERT INTO usuarios (nome_completo, cpf, email, telefone, logradouro, numero, bairro, cidade, estado, senha, tipo_usuario, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getNomeCompleto());
            ps.setString(2, entity.getCpf());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getTelefone());
            ps.setString(5, entity.getLogradouro());
            ps.setString(6, entity.getNumero());
            ps.setString(7, entity.getBairro());
            ps.setString(8, entity.getCidade());
            ps.setString(9, entity.getEstado());
            ps.setString(10, entity.getSenha());
            ps.setString(11, entity.getTipoUsuario());
            ps.setBoolean(12, entity.isAtivo());
            return ps;
        }, keyHolder);

        final Number key = keyHolder.getKey();
        return key != null ? key.intValue() : 0;
    }

    @Override
    public void remove(final int id) {
        jdbcTemplate.update("DELETE FROM usuarios WHERE id_usuario = ?", id);
    }

    @Override
    public UsuarioModel readyById(final int id) {
        final List<UsuarioModel> result = jdbcTemplate.query("SELECT * FROM usuarios WHERE id_usuario = ?", getRowMapper(), id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<UsuarioModel> readAll() {
        return jdbcTemplate.query("SELECT * FROM usuarios", getRowMapper());
    }

    @Override
    public void updateInformation(final int id, final UsuarioModel entity) {
        final String sql = "UPDATE usuarios SET nome_completo = ?, cpf = ?, email = ?, telefone = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, tipo_usuario = ?, ativo = ? WHERE id_usuario = ?";
        jdbcTemplate.update(sql,
                entity.getNomeCompleto(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getLogradouro(),
                entity.getNumero(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getTipoUsuario(),
                entity.isAtivo(),
                id);
    }

    @Override
    public UsuarioModel readByEmail(final String email) {
        if (email == null || email.isBlank()) {
            return null;
        }
        final List<UsuarioModel> result = jdbcTemplate.query("SELECT * FROM usuarios WHERE LOWER(email) = LOWER(?)", getRowMapper(), email.trim());
        return result.isEmpty() ? null : result.get(0);
    }

    private RowMapper<UsuarioModel> getRowMapper() {
        return (rs, rowNum) -> {
            final UsuarioModel usuario = new UsuarioModel();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNomeCompleto(rs.getString("nome_completo"));
            usuario.setCpf(rs.getString("cpf"));
            usuario.setEmail(rs.getString("email"));
            usuario.setTelefone(rs.getString("telefone"));
            usuario.setLogradouro(rs.getString("logradouro"));
            usuario.setNumero(rs.getString("numero"));
            usuario.setBairro(rs.getString("bairro"));
            usuario.setCidade(rs.getString("cidade"));
            usuario.setEstado(rs.getString("estado"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTipoUsuario(rs.getString("tipo_usuario"));
            usuario.setAtivo(rs.getBoolean("ativo"));
            return usuario;
        };
    }
}
