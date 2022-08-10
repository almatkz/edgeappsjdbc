CREATE SEQUENCE IF NOT EXISTS products_id_seq
    INCREMENT 1
    START 3;

CREATE TABLE IF NOT EXISTS product_positions
(
    id    INTEGER DEFAULT nextval('products_id_seq'),
    name  VARCHAR(200) NOT NULL,
    description VARCHAR(254) NOT NULL,
    price INTEGER  NOT NULL,
    CONSTRAINT product_positions_pkey PRIMARY KEY (id)
);