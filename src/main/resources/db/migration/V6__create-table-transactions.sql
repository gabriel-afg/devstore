CREATE TABLE transactions (
                              id TEXT PRIMARY KEY UNIQUE NOT NULL,
                              product_size TEXT,
                              valor INTEGER,
                              client_id TEXT,
                              FOREIGN KEY (client_id) REFERENCES users(id)
);

CREATE TABLE transaction_products (
                                      transaction_id TEXT,
                                      product_id TEXT,
                                      PRIMARY KEY (transaction_id, product_id),
                                      FOREIGN KEY (transaction_id) REFERENCES transactions(id),
                                      FOREIGN KEY (product_id) REFERENCES product(id)
);