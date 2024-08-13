CREATE TABLE sale_item (
    id SERIAL PRIMARY KEY,
    sale_id INTEGER NOT NULL REFERENCES sale(id) ON DELETE CASCADE,
    product_id INTEGER NOT NULL REFERENCES product(id) ON DELETE CASCADE,
    UNIQUE (sale_id, product_id)
);
