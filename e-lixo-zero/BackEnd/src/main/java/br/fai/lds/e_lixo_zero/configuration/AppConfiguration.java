package br.fai.lds.e_lixo_zero.configuration;

import br.fai.lds.e_lixo_zero.ports_and_adapters.adapter.dao.usuario.UsuarioFakeDaoAdapter;
import br.fai.lds.e_lixo_zero.ports_and_adapters.port.dao.usuario.UsuarioDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public UsuarioDao getUsuarioFakeDao() {
        return new UsuarioFakeDaoAdapter();
    }
}
