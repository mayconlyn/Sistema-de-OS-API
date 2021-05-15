INSERT INTO tb_product (name, price, description, created_at) VALUES ('Mensagem ao Vivo', 70.0, 'Mensagem', NOW());
INSERT INTO tb_product (name, price, description, created_at) VALUES ('Cesta de Café P', 120.0, 'Pequena', NOW()); 
INSERT INTO tb_product (name, price, description, created_at) VALUES ('Cesta de Café G', 150.0, 'Grande', NOW()); 
INSERT INTO tb_product (name, price, description, created_at) VALUES ('Mensagem Fonada', 12.0, 'Mensagem', NOW()); 

INSERT INTO tb_phone(number, type) VALUES ('42998285654', 1);

INSERT INTO tb_client(name, cpf, client_status, phone_id, street, number, district, complement) VALUES('Maria Joaquina', '08298774145', 1, 1, 'Rua das Flores', '925', 'Centro', 'Casa');

INSERT INTO tb_order (order_status, receiver, shipper, client_id, phone_receiver_id, created_at, updated_at, street, number, district, complement) VALUES (2, 'Camila', 'Maycon', 1, 1, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Rua das Flores', '925', 'Centro', 'Casa');
INSERT INTO tb_payment(pay_date, pay_time, created_at, updated_at, pay_status, pay_type, order_id) VALUES ('2021-03-30', '14:00', TIMESTAMP WITH TIME ZONE '2020-06-14T10:00:00Z', TIMESTAMP WITH TIME ZONE '2020-06-14T10:00:00Z', 1, 1, 1);

INSERT INTO tb_order_item(price, quantity, product_id, order_id) VALUES(70.0, 1, 1, 1);
INSERT INTO tb_message(cd, msg, product_id, order_id) VALUES ('12', '125', 1, 1);


