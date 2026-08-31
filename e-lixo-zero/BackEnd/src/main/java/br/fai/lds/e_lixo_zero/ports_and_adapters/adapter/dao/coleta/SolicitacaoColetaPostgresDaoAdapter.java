package br.fai.lds.e_lixo_zero.ports_and_adapters.adapter.dao.coleta;

import br.fai.lds.e_lixo_zero.domain.SolicitacaoColetaModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.coleta.SolicitacaoColetaDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class SolicitacaoColetaPostgresDaoAdapter implements SolicitacaoColetaDao {

    private final JdbcTemplate jdbcTemplate;

    public SolicitacaoColetaPostgresDaoAdapter(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int add(final SolicitacaoColetaModel entity) {
        final String sql = "INSERT INTO solicitacoes_coleta (id_usuario, id_residuo, logradouro, numero, bairro, cidade, estado, quantidade_estimada, data_desejada, status, observacoes, data_solicitacao, data_atualizacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp, current_timestamp)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entity.getUsuarioId());
            ps.setInt(2, entity.getTipoResiduoId());
            ps.setString(3, entity.getLogradouro());
            ps.setString(4, entity.getNumero());
            ps.setString(5, entity.getBairro());
            ps.setString(6, entity.getCidade());
            ps.setString(7, entity.getEstado());
            ps.setString(8, entity.getQuantidadeEstimada());
            ps.setDate(9, Date.valueOf(entity.getDataDesejada()));
            ps.setString(10, entity.getStatus());
            ps.setString(11, entity.getObservacoes());
            return ps;
        }, keyHolder);

        final Number key = keyHolder.getKey();
        return key != null ? key.intValue() : 0;
    }

    @Override
    public void remove(final int id) {
        jdbcTemplate.update("DELETE FROM solicitacoes_coleta WHERE id_solicitacao = ?", id);
    }

    @Override
    public SolicitacaoColetaModel readyById(final int id) {
        final List<SolicitacaoColetaModel> result = jdbcTemplate.query("SELECT * FROM solicitacoes_coleta WHERE id_solicitacao = ?", getRowMapper(), id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<SolicitacaoColetaModel> readAll() {
        return jdbcTemplate.query("SELECT * FROM solicitacoes_coleta", getRowMapper());
    }

    @Override
    public List<SolicitacaoColetaModel> readByUsuarioId(final int usuarioId) {
        return jdbcTemplate.query("SELECT * FROM solicitacoes_coleta WHERE id_usuario = ?", getRowMapper(), usuarioId);
    }

    @Override
    public void updateInformation(final int id, final SolicitacaoColetaModel entity) {
        final String sql = "UPDATE solicitacoes_coleta SET id_usuario = ?, id_residuo = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, quantidade_estimada = ?, data_desejada = ?, status = ?, observacoes = ?, data_atualizacao = current_timestamp WHERE id_solicitacao = ?";
        jdbcTemplate.update(sql,
                entity.getUsuarioId(),
                entity.getTipoResiduoId(),
                entity.getLogradouro(),
                entity.getNumero(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getQuantidadeEstimada(),
                Date.valueOf(entity.getDataDesejada()),
                entity.getStatus(),
                entity.getObservacoes(),
                id);
    }

    public void updateStatus(final int id, final String status) {
        jdbcTemplate.update("UPDATE solicitacoes_coleta SET status = ?, data_atualizacao = current_timestamp WHERE id_solicitacao = ?", status, id);
    }

    private RowMapper<SolicitacaoColetaModel> getRowMapper() {
        return (rs, rowNum) -> {
            final SolicitacaoColetaModel coleta = new SolicitacaoColetaModel();
            coleta.setId(rs.getInt("id_solicitacao"));
            coleta.setUsuarioId(rs.getInt("id_usuario"));
            coleta.setTipoResiduoId(rs.getInt("id_residuo"));
            final int coletorId = rs.getInt("id_coletor");
            coleta.setColetorId(rs.wasNull() ? 0 : coletorId);
            coleta.setLogradouro(rs.getString("logradouro"));
            coleta.setNumero(rs.getString("numero"));
            coleta.setBairro(rs.getString("bairro"));
            coleta.setCidade(rs.getString("cidade"));
            coleta.setEstado(rs.getString("estado"));
            coleta.setQuantidadeEstimada(rs.getString("quantidade_estimada"));
            final Date data = rs.getDate("data_desejada");
            coleta.setDataDesejada(data != null ? data.toLocalDate().toString() : null);
            coleta.setStatus(rs.getString("status"));
            coleta.setObservacoes(rs.getString("observacoes"));
            return coleta;
        };
    }
}
