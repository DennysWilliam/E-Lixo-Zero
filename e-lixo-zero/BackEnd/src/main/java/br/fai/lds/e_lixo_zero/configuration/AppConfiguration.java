package br.fai.lds.e_lixo_zero.configuration;

import br.fai.lds.e_lixo_zero.ports_and_adapters.adapter.dao.residuo.TipoResiduoPostgresDaoAdapter;
import br.fai.lds.e_lixo_zero.ports_and_adapters.adapter.dao.usuario.UsuarioPostgresDaoAdapter;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.residuo.TipoResiduoDao;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.usuario.UsuarioDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfiguration {

    @Bean
    public UsuarioDao getUsuarioPostgresDao(final DataSource dataSource) {
        return new UsuarioPostgresDaoAdapter(dataSource);
    }

    @Bean
    public TipoResiduoDao getTipoResiduoPostgresDao(final DataSource dataSource) {
        return new TipoResiduoPostgresDaoAdapter(dataSource);
    }
}
