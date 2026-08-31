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
import java.util.Arrays;
import java.util.List;

public class PontoColetaPostgresDaoAdapter implements PontoColetaDao {

    private final JdbcTemplate jdbcTemplate;

    public PontoColetaPostgresDaoAdapter(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int add(final PontoColetaModel entity) {
        final String sql = "INSERT INTO pontos_coleta (nome, logradouro, numero, bairro, cidade, estado, cep, telefone, horario_funcionamento, tipos_residuos_aceitos, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.setString(7, entity.getCep());
            ps.setString(8, entity.getTelefone());
            ps.setString(9, entity.getHorario());
            ps.setString(10, String.join(",", entity.getResiduos()));
            ps.setBoolean(11, entity.isAtivo());
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
    public void updateInformation(final int id, final PontoColetaModel entity) {
        final String sql = "UPDATE pontos_coleta SET nome = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, cep = ?, telefone = ?, horario_funcionamento = ?, tipos_residuos_aceitos = ?, ativo = ? WHERE id_ponto = ?";
        prepareDefaults(entity);
        jdbcTemplate.update(sql,
                entity.getNome(),
                entity.getLogradouro(),
                entity.getNumero(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getCep(),
                entity.getTelefone(),
                entity.getHorario(),
                String.join(",", entity.getResiduos()),
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
        if (!StringUtils.hasText(entity.getCep())) {
            entity.setCep("");
        }
        if (!StringUtils.hasText(entity.getTelefone())) {
            entity.setTelefone("");
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
            ponto.setCep(rs.getString("cep"));
            ponto.setTelefone(rs.getString("telefone"));
            ponto.setHorario(rs.getString("horario_funcionamento"));
            ponto.setAtivo(rs.getBoolean("ativo"));

            final String tipos = rs.getString("tipos_residuos_aceitos");
            if (tipos != null && !tipos.isBlank()) {
                ponto.setResiduos(Arrays.asList(tipos.split(",")));
            } else {
                ponto.setResiduos(List.of());
            }

            return ponto;
        };
    }
}
