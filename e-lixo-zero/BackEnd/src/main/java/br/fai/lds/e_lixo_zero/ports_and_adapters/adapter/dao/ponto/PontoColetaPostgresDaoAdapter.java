package br.fai.lds.e_lixo_zero.ports_and_adapters.adapter.dao.ponto;

import br.fai.lds.e_lixo_zero.domain.PontoColetaModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.ponto.PontoColetaDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class PontoColetaPostgresDaoAdapter implements PontoColetaDao {

    private final JdbcTemplate jdbcTemplate;

    public PontoColetaPostgresDaoAdapter(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int add(final PontoColetaModel entity) {
        final String sql = "INSERT INTO pontos_coleta (nome, logradouro, numero, bairro, cidade, estado, latitude, longitude, horario_funcionamento, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        prepareDefaults(entity);

        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getLogradouro());
            ps.setString(3, entity.getNumero());
            ps.setString(4, entity.getBairro());
            ps.setString(5, entity.getCidade());
            ps.setString(6, entity.getEstado());
            ps.setDouble(7, entity.getLatitude());
            ps.setDouble(8, entity.getLongitude());
            ps.setString(9, entity.getHorario());
            ps.setBoolean(10, entity.isAtivo());
            return ps;
        }, keyHolder);

        final Number key = keyHolder.getKey();
        return key != null ? key.intValue() : 0;
    }

    @Override
    public void remove(final int id) {
        jdbcTemplate.update("DELETE FROM pontos_coleta WHERE id_ponto = ?", id);
    }

    @Override
    public PontoColetaModel readyById(final int id) {
        final List<PontoColetaModel> result = jdbcTemplate.query("SELECT * FROM pontos_coleta WHERE id_ponto = ?", getRowMapper(), id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<PontoColetaModel> readAll() {
        return jdbcTemplate.query("SELECT * FROM pontos_coleta WHERE ativo = true", getRowMapper());
    }

    @Override
    public List<PontoColetaModel> readByCidade(final String cidade) {
        if (cidade == null || cidade.isBlank()) {
            return readAll();
        }
        final String termo = "%" + cidade.trim().toLowerCase() + "%";
        return jdbcTemplate.query("SELECT * FROM pontos_coleta WHERE ativo = true AND LOWER(cidade) LIKE ?", getRowMapper(), termo);
    }

    @Override
    public void updateInformation(final int id, final PontoColetaModel entity) {
        final String sql = "UPDATE pontos_coleta SET nome = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, latitude = ?, longitude = ?, horario_funcionamento = ?, ativo = ? WHERE id_ponto = ?";
        prepareDefaults(entity);
        jdbcTemplate.update(sql,
                entity.getNome(),
                entity.getLogradouro(),
                entity.getNumero(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getHorario(),
                entity.isAtivo(),
                id);
    }

    private void prepareDefaults(final PontoColetaModel entity) {
        final boolean enderecoVazio = !StringUtils.hasText(entity.getEndereco());
        if (!enderecoVazio) {
            entity.setLogradouro(entity.getEndereco().trim());
        }
        if (!StringUtils.hasText(entity.getLogradouro())) {
            entity.setLogradouro("");
        }
        if (!StringUtils.hasText(entity.getNumero())) {
            entity.setNumero("");
        }
        if (!StringUtils.hasText(entity.getBairro())) {
            entity.setBairro("");
        }
        if (!StringUtils.hasText(entity.getCidade())) {
            entity.setCidade("Santa Rita do Sapucaí");
        }
        if (!StringUtils.hasText(entity.getEstado())) {
            entity.setEstado("MG");
        }
        if (!StringUtils.hasText(entity.getHorario())) {
            entity.setHorario("");
        }
        if (entity.getResiduos() == null) {
            entity.setResiduos(List.of());
        }
    }

    private RowMapper<PontoColetaModel> getRowMapper() {
        return (rs, rowNum) -> {
            final PontoColetaModel ponto = new PontoColetaModel();
            ponto.setId(rs.getInt("id_ponto"));
            ponto.setNome(rs.getString("nome"));
            ponto.setLogradouro(rs.getString("logradouro"));
            ponto.setNumero(rs.getString("numero"));
            ponto.setBairro(rs.getString("bairro"));
            ponto.setCidade(rs.getString("cidade"));
            ponto.setEstado(rs.getString("estado"));
            ponto.setLatitude(rs.getDouble("latitude"));
            ponto.setLongitude(rs.getDouble("longitude"));
            ponto.setTelefone("");
            ponto.setHorario(rs.getString("horario_funcionamento"));
            ponto.setResiduos(List.of());
            ponto.setAtivo(rs.getBoolean("ativo"));
            return ponto;
        };
    }
}
