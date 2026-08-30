package br.fai.lds.backend.config;

import br.fai.lds.backend.entities.*;
import br.fai.lds.backend.ports_and_adpters.adpters.crud.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private TipoResiduoRepository tipoResiduoRepository;
    
    @Autowired
    private PontoColetaRepository pontoColetaRepository;
    
    @Autowired
    private SolicitacaoColetaRepository solicitacaoColetaRepository;
    
    @Autowired
    private NotificacaoRepository notificacaoRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Inicializar tipos de resíduos
        if (tipoResiduoRepository.count() == 0) {
            inicializarTiposResiduos();
        }
        
        // Inicializar usuários
        if (usuarioRepository.count() == 0) {
            inicializarUsuarios();
        }
        
        // Inicializar pontos de coleta
        if (pontoColetaRepository.count() == 0) {
            inicializarPontosColeta();
        }
        
        // Inicializar solicitações de coleta
        if (solicitacaoColetaRepository.count() == 0) {
            inicializarSolicitacoesColeta();
        }
        
        // Inicializar notificações
        if (notificacaoRepository.count() == 0) {
            inicializarNotificacoes();
        }
    }
    
    private void inicializarTiposResiduos() {
        TipoResiduo celular = new TipoResiduo("Celulares", "Eletrônicos Portáteis", "Celulares, tablets e acessórios.");
        TipoResiduo computadores = new TipoResiduo("Computadores", "Informática", "Desktops, notebooks e periféricos.");
        TipoResiduo monitores = new TipoResiduo("Monitores", "Vídeo", "Monitores e televisores.");
        TipoResiduo pilhas = new TipoResiduo("Pilhas e Baterias", "Energia", "Pilhas comuns, baterias recarregáveis e baterias de notebook.");
        TipoResiduo cabos = new TipoResiduo("Cabos e Carregadores", "Acessórios", "Cabos USB, carregadores, fontes e adaptadores.");
        TipoResiduo impressoras = new TipoResiduo("Impressoras", "Periféricos", "Impressoras jato de tinta e laser.");
        TipoResiduo teclados = new TipoResiduo("Teclados", "Periféricos", "Teclados com fio e sem fio.");
        TipoResiduo mouse = new TipoResiduo("Mouse", "Periféricos", "Mouse óptico, gamer e sem fio.");
        
        tipoResiduoRepository.save(celular);
        tipoResiduoRepository.save(computadores);
        tipoResiduoRepository.save(monitores);
        tipoResiduoRepository.save(pilhas);
        tipoResiduoRepository.save(cabos);
        tipoResiduoRepository.save(impressoras);
        tipoResiduoRepository.save(teclados);
        tipoResiduoRepository.save(mouse);
        
        System.out.println("Tipos de resíduos inicializados com sucesso!");
    }
    
    private void inicializarUsuarios() {
        Usuario joao = new Usuario("João Silva", "123.456.789-00", "joao@gmail.com", passwordEncoder.encode("123456"));
        joao.setLogradouro("Rua das Flores");
        joao.setNumero("123");
        joao.setBairro("Centro");
        joao.setCidade("Santa Rita do Sapucaí");
        
        Usuario dennys = new Usuario("Dennys", "987.654.321-00", "dennys@gmail.com", passwordEncoder.encode("1234"));
        dennys.setLogradouro("Rua 20");
        dennys.setNumero("10");
        dennys.setBairro("centro");
        dennys.setCidade("Santa Rita do Sapucaí");
        
        Usuario leo = new Usuario("Leo", "456.789.123-00", "leo@gmail.com", passwordEncoder.encode("123"));
        leo.setLogradouro("rua das estrelas");
        leo.setNumero("123");
        leo.setBairro("sao jose");
        leo.setCidade("Santa Rita do Sapucaí");
        
        usuarioRepository.save(joao);
        usuarioRepository.save(dennys);
        usuarioRepository.save(leo);
        
        System.out.println("Usuários inicializados com sucesso!");
    }
    
    private void inicializarPontosColeta() {
        TipoResiduo celular = tipoResiduoRepository.findByNome("Celulares").orElse(null);
        TipoResiduo computadores = tipoResiduoRepository.findByNome("Computadores").orElse(null);
        TipoResiduo cabos = tipoResiduoRepository.findByNome("Cabos e Carregadores").orElse(null);
        TipoResiduo pilhas = tipoResiduoRepository.findByNome("Pilhas e Baterias").orElse(null);
        TipoResiduo monitores = tipoResiduoRepository.findByNome("Monitores").orElse(null);
        TipoResiduo impressoras = tipoResiduoRepository.findByNome("Impressoras").orElse(null);
        TipoResiduo teclados = tipoResiduoRepository.findByNome("Teclados").orElse(null);
        TipoResiduo mouse = tipoResiduoRepository.findByNome("Mouse").orElse(null);
        
        PontoColeta ecoponto = new PontoColeta("Ecoponto Centro", "Rua Coronel Antônio Moreira", "Santa Rita do Sapucaí");
        ecoponto.setNumero("150");
        ecoponto.setBairro("Centro");
        ecoponto.setHorarioFuncionamento("08:00 às 17:00");
        ecoponto.setLatitude(new BigDecimal("-22.2456"));
        ecoponto.setLongitude(new BigDecimal("-45.7012"));
        if (celular != null) ecoponto.addResiduo(celular);
        if (computadores != null) ecoponto.addResiduo(computadores);
        if (cabos != null) ecoponto.addResiduo(cabos);
        
        PontoColeta coletaVerde = new PontoColeta("Coleta Verde", "Av. Sinhá Moreira", "Santa Rita do Sapucaí");
        coletaVerde.setNumero("420");
        coletaVerde.setBairro("Centro");
        coletaVerde.setHorarioFuncionamento("09:00 às 18:00");
        coletaVerde.setLatitude(new BigDecimal("-22.2500"));
        coletaVerde.setLongitude(new BigDecimal("-45.7050"));
        if (pilhas != null) coletaVerde.addResiduo(pilhas);
        if (monitores != null) coletaVerde.addResiduo(monitores);
        if (impressoras != null) coletaVerde.addResiduo(impressoras);
        
        PontoColeta reciclaTech = new PontoColeta("Recicla Tech", "Rua José Ferreira", "Santa Rita do Sapucaí");
        reciclaTech.setNumero("85");
        reciclaTech.setBairro("Família Andrade");
        reciclaTech.setHorarioFuncionamento("08:00 às 16:00");
        reciclaTech.setLatitude(new BigDecimal("-22.2480"));
        reciclaTech.setLongitude(new BigDecimal("-45.6980"));
        if (teclados != null) reciclaTech.addResiduo(teclados);
        if (mouse != null) reciclaTech.addResiduo(mouse);
        if (cabos != null) reciclaTech.addResiduo(cabos);
        
        pontoColetaRepository.save(ecoponto);
        pontoColetaRepository.save(coletaVerde);
        pontoColetaRepository.save(reciclaTech);
        
        System.out.println("Pontos de coleta inicializados com sucesso!");
    }
    
    private void inicializarSolicitacoesColeta() {
        Usuario joao = usuarioRepository.findByEmail("joao@gmail.com").orElse(null);
        TipoResiduo computadores = tipoResiduoRepository.findByNome("Computadores").orElse(null);
        TipoResiduo monitores = tipoResiduoRepository.findByNome("Monitores").orElse(null);
        TipoResiduo celulares = tipoResiduoRepository.findByNome("Celulares").orElse(null);
        
        if (joao != null && computadores != null) {
            SolicitacaoColeta coleta1 = new SolicitacaoColeta(joao, computadores, "Rua das Flores", "Centro");
            coleta1.setNumero("123");
            coleta1.setCidade("Santa Rita do Sapucaí");
            coleta1.setQuantidadeEstimada("1");
            coleta1.setDataDesejada(LocalDate.of(2026, 6, 25));
            coleta1.setStatus("Agendada");
            
            SolicitacaoColeta coleta2 = new SolicitacaoColeta(joao, monitores, "Avenida Brasil", "Jardim Verde");
            coleta2.setNumero("456");
            coleta2.setCidade("Santa Rita do Sapucaí");
            coleta2.setQuantidadeEstimada("2");
            coleta2.setDataDesejada(LocalDate.of(2026, 6, 20));
            coleta2.setStatus("Em Andamento");
            
            SolicitacaoColeta coleta3 = new SolicitacaoColeta(joao, celulares, "Rua das Nações", "Centro");
            coleta3.setNumero("789");
            coleta3.setCidade("Santa Rita do Sapucaí");
            coleta3.setQuantidadeEstimada("5");
            coleta3.setDataDesejada(LocalDate.of(2026, 6, 15));
            coleta3.setStatus("Concluída");
            
            solicitacaoColetaRepository.save(coleta1);
            solicitacaoColetaRepository.save(coleta2);
            solicitacaoColetaRepository.save(coleta3);
        }
        
        System.out.println("Solicitações de coleta inicializadas com sucesso!");
    }
    
    private void inicializarNotificacoes() {
        Usuario joao = usuarioRepository.findByEmail("joao@gmail.com").orElse(null);
        
        if (joao != null) {
            Notificacao notif1 = new Notificacao(joao, "Coleta confirmada", 
                "Sua coleta foi agendada para 25/06/2026 no período da manhã.");
            
            Notificacao notif2 = new Notificacao(joao, "Coleta em andamento", 
                "A equipe está a caminho para realizar sua coleta.");
            
            Notificacao notif3 = new Notificacao(joao, "Coleta concluída", 
                "Sua coleta foi realizada com sucesso. Obrigado por contribuir com o meio ambiente.");
            
            notificacaoRepository.save(notif1);
            notificacaoRepository.save(notif2);
            notificacaoRepository.save(notif3);
        }
        
        System.out.println("Notificações inicializadas com sucesso!");
    }
}