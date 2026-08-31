package br.fai.lds.e_lixo_zero.ports_and_adapters.adapter.dao.notificacao;

import br.fai.lds.e_lixo_zero.domain.NotificacaoModel;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.notificacao.NotificacaoDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NotificacaoPostgresDaoAdapter implements NotificacaoDao {

    private final JdbcTemplate jdbcTemplate;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public NotificacaoPostgresDaoAdapter(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int add(final NotificacaoModel entity) {
        final String sql = "INSERT INTO notificacoes (id_usuario, titulo, mensagem, tipo_notificacao, lida, data_envio) VALUES (?, ?, ?, ?, ?, current_timestamp)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id_notificacao"});
            ps.setInt(1, entity.getUsuarioId());
            ps.setString(2, entity.getTitulo());
            ps.setString(3, entity.getMensagem());
            ps.setString(4, entity.getTipoNotificacao());
            ps.setBoolean(5, entity.isLida());
            return ps;
        }, keyHolder);

        final Number key = keyHolder.getKeyAs(Number.class);
        return key != null ? key.intValue() : 0;
    }

    @Override
    public void remove(final int id) {
        jdbcTemplate.update("DELETE FROM notificacoes WHERE id_notificacao = ?", id);
    }

    @Override
    public NotificacaoModel readyById(final int id) {
        final List<NotificacaoModel> result = jdbcTemplate.query("SELECT * FROM notificacoes WHERE id_notificacao = ?", getRowMapper(), id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<NotificacaoModel> readAll() {
        return jdbcTemplate.query("SELECT * FROM notificacoes", getRowMapper());
    }

    @Override
    public List<NotificacaoModel> readByUsuarioId(final int usuarioId) {
        return jdbcTemplate.query("SELECT * FROM notificacoes WHERE id_usuario = ? ORDER BY data_envio DESC", getRowMapper(), usuarioId);
    }

    @Override
    public List<NotificacaoModel> readNaoLidasByUsuarioId(final int usuarioId) {
        return jdbcTemplate.query("SELECT * FROM notificacoes WHERE id_usuario = ? AND lida = false ORDER BY data_envio DESC", getRowMapper(), usuarioId);
    }

    @Override
    public void updateInformation(final int id, final NotificacaoModel entity) {
        final String sql = "UPDATE notificacoes SET id_usuario = ?, titulo = ?, mensagem = ?, tipo_notificacao = ?, lida = ?, data_envio = ? WHERE id_notificacao = ?";
        jdbcTemplate.update(sql,
                entity.getUsuarioId(),
                entity.getTitulo(),
                entity.getMensagem(),
                entity.getTipoNotificacao(),
                entity.isLida(),
                entity.getDataEnvio() != null ? Timestamp.valueOf(entity.getDataEnvio()) : null,
                id);
    }

    @Override
    public void marcarComoLida(final int id) {
        jdbcTemplate.update("UPDATE notificacoes SET lida = true WHERE id_notificacao = ?", id);
    }

    private RowMapper<NotificacaoModel> getRowMapper() {
        return (rs, rowNum) -> {
            final NotificacaoModel notificacao = new NotificacaoModel();
            notificacao.setId(rs.getInt("id_notificacao"));
            notificacao.setUsuarioId(rs.getInt("id_usuario"));
            notificacao.setTitulo(rs.getString("titulo"));
            notificacao.setMensagem(rs.getString("mensagem"));
            notificacao.setTipoNotificacao(rs.getString("tipo_notificacao"));
            notificacao.setLida(rs.getBoolean("lida"));
            final Timestamp dataEnvio = rs.getTimestamp("data_envio");
            notificacao.setDataEnvio(dataEnvio != null ? dataEnvio.toLocalDateTime().format(formatter) : "");
            return notificacao;
        };
    }
}
