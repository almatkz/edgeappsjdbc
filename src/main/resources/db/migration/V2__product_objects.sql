CREATE TABLE IF NOT EXISTS product_objects
(
    id    uuid  PRIMARY KEY,
    receive_date  TIMESTAMP default now(),
    expiration_date TIMESTAMP NOT NULL,
    serial_num INTEGER NOT NULL,
    isDefective BOOLEAN default FALSE,
    product_id INTEGER,
    additional_info VARCHAR(1000),
    FOREIGN KEY(product_id) REFERENCES product_positions(id)
    );

-- CREATE TABLE IF NOT EXISTS product_objects
-- (
--     id    uuid  DEFAULT nextval('products_objects_id_seq'),
--     receive_date  DATE default now(),
--     expiration_date DATE NOT NULL,
--     serial_num INTEGER NOT NULL,
--     isDefective BOOLEAN default FALSE,
--     product_id INTEGER,
--     additional_info VARCHAR(1000),
--     CONSTRAINT product_objects_pkey PRIMARY KEY (id),
--     FOREIGN KEY(product_id) REFERENCES product_positions(id)
-- );
