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
  status VARCHAR(32) NOT NULL,
  CONSTRAINT fk_products_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
  CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES categories (id)
);

INSERT INTO categories (id, name, description, creation_date) VALUES
  ('7f7b7fc8-4d7d-4a30-8a7c-0cdfb1b2c001', 'Bebidas', 'Refrigerantes, Sucos, Cerveja, Água', CURRENT_TIMESTAMP),
  ('fd8a83b9-50f8-4bba-9de6-3da3a5eac002', 'Lanches', 'Sanduíches, Refeições, Acompanhamentos', CURRENT_TIMESTAMP),
  ('9c1b1dfe-cc3a-4c4a-a4b1-2edb8f8dc003', 'Sobremesas', 'Doces em geral', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

INSERT INTO restaurants (id, name, description, creation_date) VALUES
  ('c7f0c2a2-4c66-4f9a-8a5b-11a60d29d101', 'Restaurante Sol', 'Comida caseira e variada', CURRENT_TIMESTAMP),
  ('7c2918f5-4e55-4214-ae5e-5f986d28d102', 'Cantina Bella', 'Culinária italiana tradicional', CURRENT_TIMESTAMP),
  ('67354b40-7a8b-467f-a5c3-1cd32bf3d103', 'Sushi House', 'Especialidades japonesas e sushi', CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;

INSERT INTO products (id, name, description, price, category_id, restaurant_id, creation_date, status) VALUES
  ('a14d0b86-8249-4c34-b58d-8d5c7fe3c301', 'Coca-Cola Lata 350ml', 'Refrigerante gelado', 6.50, '7f7b7fc8-4d7d-4a30-8a7c-0cdfb1b2c001', 'c7f0c2a2-4c66-4f9a-8a5b-11a60d29d101', CURRENT_TIMESTAMP, 'ACTIVE'),
  ('b7e3f290-9c39-4e0e-9f4a-e2d1b0b7c302', 'Batata Frita', 'Porção média crocante', 12.90, 'fd8a83b9-50f8-4bba-9de6-3da3a5eac002', 'c7f0c2a2-4c66-4f9a-8a5b-11a60d29d101', CURRENT_TIMESTAMP, 'ACTIVE'),
  ('c0512a8e-6c9b-4d3e-bf4e-50c67456c303', 'Cheeseburger', 'Hambúrguer com queijo', 22.50, 'fd8a83b9-50f8-4bba-9de6-3da3a5eac002', 'c7f0c2a2-4c66-4f9a-8a5b-11a60d29d101', CURRENT_TIMESTAMP, 'ACTIVE')
ON CONFLICT (id) DO NOTHING;
