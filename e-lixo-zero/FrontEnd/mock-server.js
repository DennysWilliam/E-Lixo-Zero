const express = require('express');
const cors = require('cors');
const fs = require('fs');
const path = require('path');

const app = express();
const PORT = 8087;

app.use(cors());
app.use(express.json());

const dbPath = path.join(__dirname, 'db.json');
const raw = fs.readFileSync(dbPath, 'utf8');
const db = JSON.parse(raw);

function findById(lista, id) {
  return lista.find((item) => item.id == id);
}

function nextId(lista) {
  if (!lista || lista.length === 0) return 1;
  const max = Math.max(...lista.map((item) => Number(item.id) || 0));
  return max + 1;
}

function dataHoje() {
  const hoje = new Date();
  return hoje.toISOString().split('T')[0];
}

// USUARIOS

app.post('/api/usuarios/login', (req, res) => {
  const { email, senha } = req.body;
  const usuario = db.usuarios.find((u) => u.email === email && u.senha === senha);

  if (!usuario) {
    return res.status(401).json({ message: 'E-mail ou senha inválidos' });
  }

  res.json({
    id: usuario.id,
    nomeCompleto: usuario.nomeCompleto,
    email: usuario.email,
    cpf: usuario.cpf || '',
    telefone: usuario.telefone || '',
    logradouro: usuario.logradouro || '',
    numero: usuario.numero || '',
    bairro: usuario.bairro || '',
    cidade: usuario.cidade || '',
    estado: usuario.estado || 'MG',
    tipoUsuario: usuario.tipoUsuario || 'CIDADAO',
    token: 'mock-token',
  });
});

app.get('/api/usuarios', (req, res) => {
  res.json(db.usuarios);
});

app.get('/api/usuarios/:id', (req, res) => {
  const usuario = findById(db.usuarios, req.params.id);
  if (!usuario) return res.status(404).json({ message: 'Usuário não encontrado' });
  res.json(usuario);
});

app.post('/api/usuarios', (req, res) => {
  const novo = { ...req.body, id: nextId(db.usuarios) };
  db.usuarios.push(novo);
  res.status(201).json(novo);
});

app.put('/api/usuarios/:id', (req, res) => {
  const index = db.usuarios.findIndex((u) => u.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Usuário não encontrado' });
  db.usuarios[index] = { ...req.body, id: db.usuarios[index].id };
  res.json(db.usuarios[index]);
});

app.delete('/api/usuarios/:id', (req, res) => {
  const index = db.usuarios.findIndex((u) => u.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Usuário não encontrado' });
  db.usuarios.splice(index, 1);
  res.status(204).send();
});

// PONTOS DE COLETA

app.get('/api/pontos-coleta', (req, res) => {
  res.json(db['pontos-coleta']);
});

app.get('/api/pontos-coleta/cidade/:cidade', (req, res) => {
  res.json(db['pontos-coleta']);
});

app.get('/api/pontos-coleta/:id', (req, res) => {
  const ponto = findById(db['pontos-coleta'], req.params.id);
  if (!ponto) return res.status(404).json({ message: 'Ponto de coleta não encontrado' });
  res.json(ponto);
});

app.post('/api/pontos-coleta', (req, res) => {
  const novo = { ...req.body, id: nextId(db['pontos-coleta']) };
  db['pontos-coleta'].push(novo);
  res.status(201).json(novo);
});

app.put('/api/pontos-coleta/:id', (req, res) => {
  const lista = db['pontos-coleta'];
  const index = lista.findIndex((p) => p.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Ponto de coleta não encontrado' });
  lista[index] = { ...req.body, id: lista[index].id };
  res.json(lista[index]);
});

app.delete('/api/pontos-coleta/:id', (req, res) => {
  const lista = db['pontos-coleta'];
  const index = lista.findIndex((p) => p.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Ponto de coleta não encontrado' });
  lista.splice(index, 1);
  res.status(204).send();
});

// RESIDUOS

app.get('/api/residuos', (req, res) => {
  res.json(db.residuos);
});

app.get('/api/residuos/categoria/:categoria', (req, res) => {
  const filtrados = db.residuos.filter((r) => r.categoria === req.params.categoria);
  res.json(filtrados);
});

app.get('/api/residuos/:id', (req, res) => {
  const residuo = findById(db.residuos, req.params.id);
  if (!residuo) return res.status(404).json({ message: 'Resíduo não encontrado' });
  res.json(residuo);
});

app.post('/api/residuos', (req, res) => {
  const novo = { ...req.body, id: nextId(db.residuos) };
  db.residuos.push(novo);
  res.status(201).json(novo);
});

app.put('/api/residuos/:id', (req, res) => {
  const index = db.residuos.findIndex((r) => r.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Resíduo não encontrado' });
  db.residuos[index] = { ...req.body, id: db.residuos[index].id };
  res.json(db.residuos[index]);
});

app.delete('/api/residuos/:id', (req, res) => {
  const index = db.residuos.findIndex((r) => r.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Resíduo não encontrado' });
  db.residuos.splice(index, 1);
  res.status(204).send();
});

// COLETAS

app.get('/api/coletas', (req, res) => {
  res.json(db.coletas);
});

app.get('/api/coletas/:id', (req, res) => {
  const coleta = findById(db.coletas, req.params.id);
  if (!coleta) return res.status(404).json({ message: 'Coleta não encontrada' });
  res.json(coleta);
});

app.post('/api/coletas', (req, res) => {
  const nova = { ...req.body, id: String(nextId(db.coletas)) };
  db.coletas.push(nova);

  const residuoNome = req.body.residuo || 'Resíduo';
  const dataFormatada = req.body.data ? req.body.data.split('-').reverse().join('/') : dataHoje();
  const periodo = req.body.periodo || 'Manhã';

  db.notificacoes.push({
    id: nextId(db.notificacoes),
    usuarioId: req.body.usuarioId || 1,
    titulo: 'Coleta confirmada',
    mensagem: `Sua coleta de ${residuoNome} foi agendada para ${dataFormatada} no período da ${periodo.toLowerCase()}.`,
    tipoNotificacao: 'INFORMATIVA',
    lida: false,
    dataEnvio: dataHoje(),
    data: dataHoje(),
  });

  res.status(201).json(nova);
});

app.put('/api/coletas/:id/status', (req, res) => {
  const index = db.coletas.findIndex((c) => c.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Coleta não encontrada' });
  db.coletas[index].status = req.body.status;
  res.json(db.coletas[index]);
});

app.put('/api/coletas/:id', (req, res) => {
  const index = db.coletas.findIndex((c) => c.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Coleta não encontrada' });
  db.coletas[index] = { ...req.body, id: db.coletas[index].id };
  res.json(db.coletas[index]);
});

app.delete('/api/coletas/:id', (req, res) => {
  const index = db.coletas.findIndex((c) => c.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Coleta não encontrada' });
  db.coletas.splice(index, 1);
  res.status(204).send();
});

// NOTIFICACOES

app.get('/api/notificacoes', (req, res) => {
  res.json(db.notificacoes);
});

app.get('/api/notificacoes/usuario/:usuarioId/nao-lidas', (req, res) => {
  res.json(db.notificacoes.filter((n) => !n.lida));
});

app.get('/api/notificacoes/usuario/:usuarioId', (req, res) => {
  res.json(db.notificacoes);
});

app.get('/api/notificacoes/:id', (req, res) => {
  const notificacao = findById(db.notificacoes, req.params.id);
  if (!notificacao) return res.status(404).json({ message: 'Notificação não encontrada' });
  res.json(notificacao);
});

app.post('/api/notificacoes', (req, res) => {
  const nova = { ...req.body, id: nextId(db.notificacoes) };
  db.notificacoes.push(nova);
  res.status(201).json(nova);
});

app.put('/api/notificacoes/:id/marcar-lida', (req, res) => {
  const index = db.notificacoes.findIndex((n) => n.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Notificação não encontrada' });
  db.notificacoes[index].lida = true;
  res.json(db.notificacoes[index]);
});

app.put('/api/notificacoes/:id', (req, res) => {
  const index = db.notificacoes.findIndex((n) => n.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Notificação não encontrada' });
  db.notificacoes[index] = { ...req.body, id: db.notificacoes[index].id };
  res.json(db.notificacoes[index]);
});

app.delete('/api/notificacoes/:id', (req, res) => {
  const index = db.notificacoes.findIndex((n) => n.id == req.params.id);
  if (index < 0) return res.status(404).json({ message: 'Notificação não encontrada' });
  db.notificacoes.splice(index, 1);
  res.status(204).send();
});

app.listen(PORT, () => {
  console.log(`Mock server rodando em http://localhost:${PORT}`);
});
