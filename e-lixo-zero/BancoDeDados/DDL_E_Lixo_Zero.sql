-- ============================================================
-- DDL - Banco de Dados do Sistema E-Lixo Zero
-- SGBD: PostgreSQL
-- ============================================================

CREATE TABLE usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nome_completo VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefone VARCHAR(20),

    logradouro VARCHAR(150),
    numero VARCHAR(20),
    bairro VARCHAR(100),
    cidade VARCHAR(100) DEFAULT 'Santa Rita do Sapucaí',
    estado CHAR(2) DEFAULT 'MG',

    senha VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(20) NOT NULL DEFAULT 'CIDADAO',
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_tipo_usuario
        CHECK (tipo_usuario IN ('CIDADAO', 'ADMINISTRADOR', 'COLETOR'))
);

CREATE TABLE tipos_residuos (
    id_residuo SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    categoria VARCHAR(100),
    descricao TEXT,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE pontos_coleta (
    id_ponto SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,

    logradouro VARCHAR(150) NOT NULL,
    numero VARCHAR(20),
    bairro VARCHAR(100),
    cidade VARCHAR(100) NOT NULL DEFAULT 'Santa Rita do Sapucaí',
    estado CHAR(2) NOT NULL DEFAULT 'MG',

    horario_funcionamento VARCHAR(150),
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ponto_coleta_residuo (
    id_ponto INT NOT NULL,
    id_residuo INT NOT NULL,

    PRIMARY KEY (id_ponto, id_residuo),

    CONSTRAINT fk_ponto_residuo_ponto
        FOREIGN KEY (id_ponto)
        REFERENCES pontos_coleta (id_ponto)
        ON DELETE CASCADE,

    CONSTRAINT fk_ponto_residuo_residuo
        FOREIGN KEY (id_residuo)
        REFERENCES tipos_residuos (id_residuo)
        ON DELETE CASCADE
);

CREATE TABLE solicitacoes_coleta (
    id_solicitacao SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_residuo INT NOT NULL,
    id_coletor INT,

    logradouro VARCHAR(150) NOT NULL,
    numero VARCHAR(20),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL DEFAULT 'Santa Rita do Sapucaí',
    estado CHAR(2) NOT NULL DEFAULT 'MG',

    quantidade_estimada VARCHAR(100),
    data_desejada DATE,
    status VARCHAR(30) NOT NULL DEFAULT 'PENDENTE',
    observacoes TEXT,
    data_solicitacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,

    CONSTRAINT fk_solicitacao_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuarios (id_usuario),

    CONSTRAINT fk_solicitacao_residuo
        FOREIGN KEY (id_residuo)
        REFERENCES tipos_residuos (id_residuo),

    CONSTRAINT fk_solicitacao_coletor
        FOREIGN KEY (id_coletor)
        REFERENCES usuarios (id_usuario),

    CONSTRAINT chk_status_solicitacao
        CHECK (status IN ('PENDENTE', 'ACEITA', 'EM_ANDAMENTO', 'CONCLUIDA', 'CANCELADA'))
);

CREATE TABLE historico_solicitacao (
    id_historico SERIAL PRIMARY KEY,
    id_solicitacao INT NOT NULL,
    id_usuario_responsavel INT,
    status_anterior VARCHAR(30),
    status_novo VARCHAR(30) NOT NULL,
    observacao TEXT,
    data_alteracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_historico_solicitacao
        FOREIGN KEY (id_solicitacao)
        REFERENCES solicitacoes_coleta (id_solicitacao)
        ON DELETE CASCADE,

    CONSTRAINT fk_historico_usuario
        FOREIGN KEY (id_usuario_responsavel)
        REFERENCES usuarios (id_usuario),

    CONSTRAINT chk_status_historico_anterior
        CHECK (
            status_anterior IS NULL OR 
            status_anterior IN ('PENDENTE', 'ACEITA', 'EM_ANDAMENTO', 'CONCLUIDA', 'CANCELADA')
        ),

    CONSTRAINT chk_status_historico_novo
        CHECK (status_novo IN ('PENDENTE', 'ACEITA', 'EM_ANDAMENTO', 'CONCLUIDA', 'CANCELADA'))
);

CREATE TABLE notificacoes (
    id_notificacao SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    mensagem TEXT NOT NULL,
    tipo_notificacao VARCHAR(30) NOT NULL DEFAULT 'INFORMATIVA',
    lida BOOLEAN NOT NULL DEFAULT FALSE,
    data_envio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_notificacao_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuarios (id_usuario)
        ON DELETE CASCADE,

    CONSTRAINT chk_tipo_notificacao
        CHECK (tipo_notificacao IN ('INFORMATIVA', 'CAMPANHA', 'COLETA', 'PROXIMIDADE'))
);

CREATE INDEX idx_usuarios_email ON usuarios (email);
CREATE INDEX idx_usuarios_cpf ON usuarios (cpf);
CREATE INDEX idx_pontos_coleta_cidade ON pontos_coleta (cidade);
CREATE INDEX idx_solicitacoes_usuario ON solicitacoes_coleta (id_usuario);
CREATE INDEX idx_solicitacoes_coletor ON solicitacoes_coleta (id_coletor);
CREATE INDEX idx_solicitacoes_status ON solicitacoes_coleta (status);
CREATE INDEX idx_notificacoes_usuario ON notificacoes (id_usuario);

INSERT INTO tipos_residuos (nome, categoria, descricao) VALUES
('Celular', 'Dispositivo eletrônico', 'Aparelhos celulares antigos, quebrados ou sem uso.'),
('Bateria', 'Componente eletrônico', 'Baterias de celulares, notebooks e outros equipamentos.'),
('Carregador', 'Acessório eletrônico', 'Carregadores, fontes e cabos de energia.'),
('Computador', 'Equipamento eletrônico', 'Computadores, notebooks e peças relacionadas.'),
('Televisão', 'Equipamento eletrônico', 'Televisores e monitores sem utilização.');