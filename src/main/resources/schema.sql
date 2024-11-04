CREATE TABLE stock_exchange (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(255) NOT NULL,
                                description VARCHAR(255),
                                live_in_market BOOLEAN DEFAULT FALSE
);

CREATE TABLE stock (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description VARCHAR(255),
                       current_price DECIMAL(19, 2),
                       last_update TIMESTAMP
);

CREATE TABLE stock_exchange_mapping (
                                        stock_id BIGINT,
                                        stock_exchange_id BIGINT,
                                        FOREIGN KEY (stock_id) REFERENCES stock(id) ON DELETE CASCADE,
                                        FOREIGN KEY (stock_exchange_id) REFERENCES stock_exchange(id) ON DELETE CASCADE,
                                        PRIMARY KEY (stock_id, stock_exchange_id)
);


CREATE TABLE stock_USER (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255)
);
