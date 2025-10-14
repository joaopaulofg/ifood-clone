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
  cpf VARCHAR(255) NOT NULL,
  creation_date TIMESTAMP NOT NULL
);

-- Seed Categories
INSERT INTO categories (id, name, description, creation_date) VALUES
  ('cat-001', 'Bebidas', 'Drinks and refreshments', CURRENT_TIMESTAMP),
  ('cat-002', 'Lanches', 'Snacks, sandwiches and burgers', CURRENT_TIMESTAMP),
  ('cat-003', 'Sobremesas', 'Desserts and sweets', CURRENT_TIMESTAMP),
  ('cat-004', 'Massas', 'Pasta dishes', CURRENT_TIMESTAMP),
  ('cat-005', 'Sushis', 'Japanese cuisine and sushi', CURRENT_TIMESTAMP);

-- Seed Restaurants
INSERT INTO restaurants (id, name, description, creation_date) VALUES
  ('res-001', 'Restaurante Sol', 'Comida caseira e variada', CURRENT_TIMESTAMP),
  ('res-002', 'Cantina Bella', 'Culinária italiana tradicional', CURRENT_TIMESTAMP),
  ('res-003', 'Sushi House', 'Especialidades japonesas e sushi', CURRENT_TIMESTAMP),
  ('res-004', 'Burger Town', 'Hambúrgueres artesanais e batatas', CURRENT_TIMESTAMP),
  ('res-005', 'Veggie Garden', 'Opções vegetarianas e veganas', CURRENT_TIMESTAMP);