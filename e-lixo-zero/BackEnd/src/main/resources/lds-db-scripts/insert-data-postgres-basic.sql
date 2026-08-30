INSERT INTO usuarios (nome_completo, email, senha, tipo_usuario)
VALUES ('João Silva', 'joao@gmail.com', '123456', 'CIDADAO');

INSERT INTO usuarios (nome_completo, email, senha, tipo_usuario)
VALUES ('Maria Santos', 'maria@gmail.com', '123456', 'CIDADAO');

INSERT INTO tipos_residuos (nome, categoria, descricao)
VALUES ('Celulares', 'Eletrônicos', 'Telefones celulares e smartphones');

INSERT INTO tipos_residuos (nome, categoria, descricao)
VALUES ('Computadores', 'Eletrônicos', 'Computadores desktop e laptops');

INSERT INTO tipos_residuos (nome, categoria, descricao)
VALUES ('Baterias', 'Perigosos', 'Baterias de diversos tipos');

INSERT INTO pontos_coleta (nome, logradouro, numero, bairro, cidade, estado, telefone, horario_funcionamento)
VALUES ('EcoPoint Centro', 'Rua Principal', '100', 'Centro', 'Santa Rita do Sapucaí', 'MG', '3534712345', 'Seg-Sex 8h-18h');

INSERT INTO pontos_coleta (nome, logradouro, numero, bairro, cidade, estado, telefone, horario_funcionamento)
VALUES ('EcoPoint Zona Sul', 'Avenida Sul', '200', 'Zona Sul', 'Santa Rita do Sapucaí', 'MG', '3534712346', 'Seg-Sex 9h-17h');