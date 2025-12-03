-- Schema: create tables if not exists\

-- Clients
CREATE TABLE IF NOT EXISTS clients (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  cpf VARCHAR(255) NOT NULL UNIQUE,
  creation_date TIMESTAMP NOT NULL
);

-- Seed Clients
INSERT INTO clients (id, name, email, cpf, creation_date) VALUES
  ('5d9dc8ab-3be0-4b3a-90fb-3b422fe1c201', 'Maria Silva', 'maria.silva@example.com', '11111111111', CURRENT_TIMESTAMP),
  ('c088e0fb-3f0d-428d-9f1b-878a3ef6c202', 'João Santos', 'joao.santos@example.com', '22222222222', CURRENT_TIMESTAMP),
  ('b2d12c8d-6e2b-4cf2-86c0-e4b9fa9fc203', 'Ana Pereira', 'ana.pereira@example.com', '33333333333', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;