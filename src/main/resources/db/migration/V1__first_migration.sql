CREATE TABLE IF NOT EXISTS product_positions
(
    id    BIGINT PRIMARY KEY,
    name  VARCHAR(200) NOT NULL,
    description VARCHAR(254) NOT NULL,
    price NUMERIC  NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS products_id_seq
    INCREMENT 1
    START 3
    OWNED BY product_positions.id;