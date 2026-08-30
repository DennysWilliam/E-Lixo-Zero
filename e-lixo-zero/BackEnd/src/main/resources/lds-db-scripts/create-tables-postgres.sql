DROP TABLE IF EXISTS solicitacoes_coleta;
DROP TABLE IF EXISTS tipos_residuos;
DROP TABLE IF EXISTS pontos_coleta;
DROP TABLE IF EXISTS notificacoes;
DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios
(
    id_usuario SERIAL not null,
    nome_completo varchar(150) not null,
    cpf varchar(14) unique,
    email varchar(150) not null unique,
    telefone varchar(20),
    logradouro varchar(150),
    numero varchar(20),
    bairro varchar(100),
    cidade varchar(100) default 'Santa Rita do Sapucaí',
    estado varchar(2) default 'MG',
    senha varchar(100) not null,
    tipo_usuario varchar(20) default 'CIDADAO',
    ativo boolean default true,
    data_cadastro timestamp default current_timestamp,
    PRIMARY KEY (id_usuario)
);

CREATE TABLE tipos_residuos
(
    id_residuo SERIAL not null,
    nome varchar(100) not null unique,
    categoria varchar(100),
    descricao text,
    ativo boolean default true,
    PRIMARY KEY (id_residuo)
);

CREATE TABLE pontos_coleta
(
    id_ponto SERIAL not null,
    nome varchar(150) not null,
    logradouro varchar(150) not null,
    numero varchar(20),
    bairro varchar(100),
    cidade varchar(100) default 'Santa Rita do Sapucaí',
    estado varchar(2) default 'MG',
    cep varchar(10),
    telefone varchar(20),
    horario_funcionamento varchar(100),
    tipos_residuos_aceitos text,
    ativo boolean default true,
    PRIMARY KEY (id_ponto)
);

CREATE TABLE solicitacoes_coleta
(
    id_solicitacao SERIAL not null,
    id_usuario int not null,
    id_residuo int not null,
    id_coletor int,
    logradouro varchar(150) not null,
    numero varchar(20),
    bairro varchar(100) not null,
    cidade varchar(100) default 'Santa Rita do Sapucaí',
    estado varchar(2) default 'MG',
    quantidade_estimada varchar(100),
    data_desejada date,
    status varchar(30) default 'PENDENTE',
    observacoes text,
    data_solicitacao timestamp default current_timestamp,
    data_atualizacao timestamp,
    PRIMARY KEY (id_solicitacao),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_residuo) REFERENCES tipos_residuos(id_residuo),
    FOREIGN KEY (id_coletor) REFERENCES usuarios(id_usuario)
);

CREATE TABLE notificacoes
(
    id_notificacao SERIAL not null,
    id_usuario int not null,
    titulo varchar(100) not null,
    mensagem text not null,
    tipo_notificacao varchar(30) default 'INFORMATIVA',
    lida boolean default false,
    data_envio timestamp default current_timestamp,
    PRIMARY KEY (id_notificacao),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);