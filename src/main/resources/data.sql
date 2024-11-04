INSERT INTO stock_exchange (name, description, live_in_market) VALUES
                                                                   ('NYSE', 'New York Stock Exchange', TRUE),
                                                                   ('NASDAQ', 'NASDAQ Stock Market', TRUE),
                                                                   ('LSE', 'London Stock Exchange', FALSE);

INSERT INTO stock (name, description, current_price, last_update) VALUES
                                                                      ('Apple Inc.', 'Technology Company', 150.00, CURRENT_TIMESTAMP),
                                                                      ('Microsoft Corp.', 'Technology Company', 250.00, CURRENT_TIMESTAMP),
                                                                      ('Tesla Inc.', 'Electric Vehicle Company', 700.00, CURRENT_TIMESTAMP);

INSERT INTO stock_exchange_mapping (stock_id, stock_exchange_id) VALUES
                                                                     (1, 1), (1, 2), (2, 1), (3, 2);



INSERT INTO stock_user (id, username,password) VALUES
                                                                     (1, 'ahmed','P@ssw0rd'), (2, 'ali','P@ssw0rd'), (3, 'admin','P@ssw0rd');
