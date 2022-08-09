CREATE TABLE IF NOT EXISTS product_objects
(
    id    uuid  PRIMARY KEY,
    receive_date  DATE default now(),
    expiration_date DATE NOT NULL,
    serial_num INTEGER NOT NULL,
    isDefective BOOLEAN default FALSE,
    product_id INTEGER,
    additional_info VARCHAR(1000) NOT NULL,
    FOREIGN KEY(product_id) REFERENCES product_positions(id)
    );