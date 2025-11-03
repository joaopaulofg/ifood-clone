-- Schema: create tables if not exists
CREATE TABLE IF NOT EXISTS categories (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  creation_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS restaurants (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  creation_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  price NUMERIC(10, 2) NOT NULL,
  category_id VARCHAR(255) NOT NULL,
  restaurant_id VARCHAR(255) NOT NULL,
  creation_date TIMESTAMP NOT NULL,
  status VARCHAR(50) NOT NULL,
  CONSTRAINT fk_products_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
  CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Clients
CREATE TABLE IF NOT EXISTS clients (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  cpf VARCHAR(255) NOT NULL UNIQUE,
  creation_date TIMESTAMP NOT NULL
);

-- Seed Categories
INSERT INTO categories (id, name, description, creation_date) VALUES
  ('7f7b7fc8-4d7d-4a30-8a7c-0cdfb1b2c001', 'Bebidas', 'Refrigerantes, Sucos, Cerveja, Água', CURRENT_TIMESTAMP),
  ('fd8a83b9-50f8-4bba-9de6-3da3a5eac002', 'Lanches', 'Sanduíches, Refeições, Acompanhamentos', CURRENT_TIMESTAMP),
  ('9c1b1dfe-cc3a-4c4a-a4b1-2edb8f8dc003', 'Sobremesas', 'Doces em geral', CURRENT_TIMESTAMP),
  ('a3682fb3-8f7a-4a88-9f2a-2fbdaf89c004', 'Sushis', 'Comida Japonesa', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

-- Seed Restaurants
INSERT INTO restaurants (id, name, description, creation_date) VALUES
  ('c7f0c2a2-4c66-4f9a-8a5b-11a60d29d101', 'Restaurante Sol', 'Comida caseira e variada', CURRENT_TIMESTAMP),
  ('7c2918f5-4e55-4214-ae5e-5f986d28d102', 'Cantina Bella', 'Culinária italiana tradicional', CURRENT_TIMESTAMP),
  ('67354b40-7a8b-467f-a5c3-1cd32bf3d103', 'Sushi House', 'Especialidades japonesas e sushi', CURRENT_TIMESTAMP),
  ('6e6a6a3a-1b29-4b2c-8f80-bfd3b0e2d104', 'Burger Town', 'Hambúrgueres artesanais e batatas', CURRENT_TIMESTAMP),
  ('f85fca5d-8693-4f04-8f70-0f493d86d105', 'Veggie Garden', 'Opções vegetarianas e veganas', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

-- Seed Products
INSERT INTO products (id, name, description, price, category_id, restaurant_id, creation_date, status) VALUES
  ('a14d0b86-8249-4c34-b58d-8d5c7fe3c301', 'Coca-Cola Lata 350ml', 'Refrigerante gelado', 6.50, '7f7b7fc8-4d7d-4a30-8a7c-0cdfb1b2c001', '6e6a6a3a-1b29-4b2c-8f80-bfd3b0e2d104', CURRENT_TIMESTAMP, 'ACTIVE'),
  ('b7e3f290-9c39-4e0e-9f4a-e2d1b0b7c302', 'Batata Frita', 'Porção média crocante', 12.90, 'fd8a83b9-50f8-4bba-9de6-3da3a5eac002', '6e6a6a3a-1b29-4b2c-8f80-bfd3b0e2d104', CURRENT_TIMESTAMP, 'ACTIVE'),
  ('c0512a8e-6c9b-4d3e-bf4e-50c67456c303', 'Cheeseburger', 'Hambúrguer com queijo', 22.50, 'fd8a83b9-50f8-4bba-9de6-3da3a5eac002', '6e6a6a3a-1b29-4b2c-8f80-bfd3b0e2d104', CURRENT_TIMESTAMP, 'ACTIVE'),
  ('d2f3a9bd-3e8f-4d63-9d23-1e3b8f7ac304', 'Sushi Combo 12 peças', 'Variedade de sushis', 39.90, 'a3682fb3-8f7a-4a88-9f2a-2fbdaf89c004', '67354b40-7a8b-467f-a5c3-1cd32bf3d103', CURRENT_TIMESTAMP, 'ACTIVE'),
  ('e3a5c7df-4b2c-49c1-8b6d-9b4e2d1fc305', 'Tiramisu', 'Sobremesa italiana', 18.00, '9c1b1dfe-cc3a-4c4a-a4b1-2edb8f8dc003', '7c2918f5-4e55-4214-ae5e-5f986d28d102', CURRENT_TIMESTAMP, 'ACTIVE')
ON CONFLICT (id) DO NOTHING;

-- Seed Clients
INSERT INTO clients (id, name, email, cpf, creation_date) VALUES
  ('5d9dc8ab-3be0-4b3a-90fb-3b422fe1c201', 'Maria Silva', 'maria.silva@example.com', '11111111111', CURRENT_TIMESTAMP),
  ('c088e0fb-3f0d-428d-9f1b-878a3ef6c202', 'João Santos', 'joao.santos@example.com', '22222222222', CURRENT_TIMESTAMP),
  ('b2d12c8d-6e2b-4cf2-86c0-e4b9fa9fc203', 'Ana Pereira', 'ana.pereira@example.com', '33333333333', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;