INSERT INTO restaurant (id, cep, complement, name) VALUES
(1L, '0000001', 'Complemento Endereço Restaurante 1', 'Picanha Mania'),
(2L, '0000002', 'Complemento Endereço Restaurante 2', 'Morada do Peixe');

INSERT INTO client (id, cep, complement, name) VALUES
(1L, '0000001', 'Rua da Paz', 'Endy');

INSERT INTO product (id, available, name, unitary_value, restaurant_id) VALUES
(1L, true, 'Tambaqui Assado', 5.0, 1L),
(2L, true, 'Picanha na pedra', 6.0, 1L),
(3L, true, 'Lasanha', 7.0, 2L);

INSERT INTO bag (id, form_payment, closed, amounts, client_id) VALUES
(1L, 0, false, 0.0, 1L);