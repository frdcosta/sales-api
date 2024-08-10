CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL CHECK (LENGTH(name) >= 3),
    email VARCHAR(255) NOT NULL UNIQUE CHECK (email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$'),
    date_of_birth DATE NOT NULL CHECK (date_of_birth < CURRENT_DATE),
    phone VARCHAR(20),
    address VARCHAR(255) NOT NULL CHECK (LENGTH(address) >= 10)
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL CHECK (LENGTH(name) >= 3),
    description VARCHAR(255),
    price DECIMAL(15, 2) NOT NULL CHECK (price > 0),
    barcode CHAR(13) NOT NULL,
    manufacturing_date DATE NOT NULL CHECK (manufacturing_date < CURRENT_DATE)
);

CREATE TABLE sale (
    id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL REFERENCES client(id) ON DELETE CASCADE,
    sale_date TIMESTAMP NOT NULL,
    total_value DECIMAL(15, 2) NOT NULL CHECK (total_value > 0),
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'FINALIZED', 'CANCELLED'))
);