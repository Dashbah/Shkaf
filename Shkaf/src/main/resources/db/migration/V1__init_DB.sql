-- Create sequences
CREATE SEQUENCE bucket_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE category_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE order_details_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE order_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE product_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 50;

-- Create tables
CREATE TABLE buckets (
                         id BIGINT NOT NULL,
                         user_id BIGINT UNIQUE,
                         PRIMARY KEY (id)
);

CREATE TABLE buckets_products (
                                  bucket_id BIGINT NOT NULL,
                                  product_id BIGINT NOT NULL
);

CREATE TABLE categories (
                            id BIGINT NOT NULL,
                            title VARCHAR(255),
                            PRIMARY KEY (id)
);

CREATE TABLE order_details (
                               amount NUMERIC(38,2),
                               price NUMERIC(38,2),
                               id BIGINT NOT NULL,
                               order_id BIGINT,
                               product_id BIGINT,
                               PRIMARY KEY (id)
);

CREATE TABLE orders (
                        sum NUMERIC(38,2),
                        created_at TIMESTAMP(6),
                        id BIGINT NOT NULL,
                        updated_at TIMESTAMP(6),
                        user_id BIGINT,
                        address VARCHAR(255),
                        status VARCHAR(255) CHECK (status IN ('NEW','APPROVED','CANCELED','PAID','CLOSED')),
                        PRIMARY KEY (id)
);

CREATE TABLE orders_details (
                                details_id BIGINT NOT NULL UNIQUE,
                                order_id BIGINT NOT NULL
);

CREATE TABLE products (
                          price NUMERIC(38,2),
                          id BIGINT NOT NULL,
                          title VARCHAR(255),
                          PRIMARY KEY (id)
);

CREATE TABLE products_categories (
                                     category_id BIGINT NOT NULL,
                                     product_id BIGINT NOT NULL
);

CREATE TABLE users (
                       is_archived BOOLEAN NOT NULL,
                       id BIGINT NOT NULL,
                       email VARCHAR(255),
                       name VARCHAR(255),
                       password VARCHAR(255),
                       role VARCHAR(255) CHECK (role IN ('CLIENT','MANAGER','ADMIN')),
                       PRIMARY KEY (id)
);

-- Add foreign key constraints
ALTER TABLE IF EXISTS buckets ADD CONSTRAINT fk_buckets_user FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE IF EXISTS buckets_products ADD CONSTRAINT fk_buckets_products_bucket FOREIGN KEY (bucket_id) REFERENCES buckets(id);
ALTER TABLE IF EXISTS buckets_products ADD CONSTRAINT fk_buckets_products_product FOREIGN KEY (product_id) REFERENCES products(id);
ALTER TABLE IF EXISTS order_details ADD CONSTRAINT fk_order_details_order FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE IF EXISTS order_details ADD CONSTRAINT fk_order_details_product FOREIGN KEY (product_id) REFERENCES products(id);
ALTER TABLE IF EXISTS orders ADD CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE IF EXISTS orders_details ADD CONSTRAINT fk_orders_details_details FOREIGN KEY (details_id) REFERENCES order_details(id);
ALTER TABLE IF EXISTS orders_details ADD CONSTRAINT fk_orders_details_order FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE IF EXISTS products_categories ADD CONSTRAINT fk_products_categories_category FOREIGN KEY (category_id) REFERENCES categories(id);
ALTER TABLE IF EXISTS products_categories ADD CONSTRAINT fk_products_categories_product FOREIGN KEY (product_id) REFERENCES products(id);