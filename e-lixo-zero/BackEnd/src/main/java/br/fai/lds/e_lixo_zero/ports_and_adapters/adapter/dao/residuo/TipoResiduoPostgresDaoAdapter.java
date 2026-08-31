package br.fai.lds.e_lixo_zero.ports_and_adapters.adapter.dao.residuo;

import br.fai.lds.e_lixo_zero.domain.TipoResiduoModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.residuo.TipoResiduoDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class TipoResiduoPostgresDaoAdapter implements TipoResiduoDao {

    private final JdbcTemplate jdbcTemplate;

    public TipoResiduoPostgresDaoAdapter(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int add(final TipoResiduoModel entity) {
        final String sql = "INSERT INTO tipos_residuos (nome, categoria, descricao, ativo) VALUES (?, ?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getCategoria());
            ps.setString(3, entity.getDescricao());
            ps.setBoolean(4, entity.isAtivo());
            return ps;
        }, keyHolder);

        final Number key = keyHolder.getKey();
        return key != null ? key.intValue() : 0;
    }

    @Override
    public void remove(final int id) {
        jdbcTemplate.update("DELETE FROM tipos_residuos WHERE id_residuo = ?", id);
    }

    @Override
    public TipoResiduoModel readyById(final int id) {
        final List<TipoResiduoModel> result = jdbcTemplate.query("SELECT * FROM tipos_residuos WHERE id_residuo = ?", getRowMapper(), id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<TipoResiduoModel> readAll() {
        return jdbcTemplate.query("SELECT * FROM tipos_residuos WHERE ativo = true", getRowMapper());
    }

    @Override
    public void updateInformation(final int id, final TipoResiduoModel entity) {
        final String sql = "UPDATE tipos_residuos SET nome = ?, categoria = ?, descricao = ?, ativo = ? WHERE id_residuo = ?";
        jdbcTemplate.update(sql, entity.getNome(), entity.getCategoria(), entity.getDescricao(), entity.isAtivo(), id);
    }

    private RowMapper<TipoResiduoModel> getRowMapper() {
        return (rs, rowNum) -> {
            final TipoResiduoModel residuo = new TipoResiduoModel();
            residuo.setId(rs.getInt("id_residuo"));
            residuo.setNome(rs.getString("nome"));
            residuo.setCategoria(rs.getString("categoria"));
            residuo.setDescricao(rs.getString("descricao"));
            residuo.setAtivo(rs.getBoolean("ativo"));
            return residuo;
        };
    }
}
