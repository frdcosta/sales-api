ALTER TABLE sale
ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE client
ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE product
ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
