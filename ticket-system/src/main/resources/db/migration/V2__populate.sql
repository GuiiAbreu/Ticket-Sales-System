-- Inserir organizadores
INSERT INTO organizador (login, email, nome, senha) VALUES
                                                        ('org1', 'org1@example.com', 'Organizador 1', 'senha123'),
                                                        ('org2', 'org2@example.com', 'Organizador 2', 'senha456');

-- Inserir clientes
INSERT INTO cliente (login, email, nome, senha) VALUES
                                                    ('cliente1', 'cliente1@example.com', 'João Silva', 'senha123'),
                                                    ('cliente2', 'cliente2@example.com', 'Maria Oliveira', 'senha456');

-- Inserir eventos
INSERT INTO evento (capacidade_max, horario, horario_fim, descricao, local, nome) VALUES
                                                                                      (100, '18:00:00', '22:00:00', 'Show de Rock', 'Arena Show', 'Rock in Rio'),
                                                                                      (50, '20:00:00', '23:00:00', 'Teatro Clássico', 'Teatro Municipal', 'Hamlet');

-- Inserir datas de eventos
INSERT INTO datas_evento (data) VALUES
                                    ('2023-12-15'),
                                    ('2023-12-16'),
                                    ('2024-01-10');

-- Associar eventos a datas
INSERT INTO evento_data_evento (datas_evento_id, evento_id) VALUES
                                                                (1, 1), -- Rock in Rio no dia 15/12/2023
                                                                (2, 1), -- Rock in Rio no dia 16/12/2023
                                                                (3, 2); -- Hamlet no dia 10/01/2024

-- Associar organizadores a eventos
INSERT INTO evento_organizador (evento_id, organizador_id) VALUES
                                                               (1, 1), -- Organizador 1 para Rock in Rio
                                                               (2, 2); -- Organizador 2 para Hamlet

-- Inserir ingressos
INSERT INTO ingressos (dias, evento_id) VALUES
                                            (1, 1), -- Ingresso para 1 dia no Rock in Rio
                                            (2, 1), -- Ingresso para 2 dias no Rock in Rio
                                            (1, 2); -- Ingresso para 1 dia no Hamlet

-- Inserir modalidades de ingressos
INSERT INTO modalidade (ingressos_disponiveis, preco, ingresso_id, tipo_modalidade) VALUES
                                                                                        (50, 200.00, 1, 'Pista'), -- Pista para Rock in Rio (1 dia)
                                                                                        (30, 350.00, 2, 'VIP'),   -- VIP para Rock in Rio (2 dias)
                                                                                        (20, 150.00, 3, 'Plateia'); -- Plateia para Hamlet

-- Inserir campos (perguntas ou informações adicionais)
INSERT INTO campo (modalidade_id, descricao_campo, nome_campo) VALUES
                                                                   (1, 'Informe seu nome completo', 'Nome Completo'),
                                                                   (2, 'Informe seu RG', 'RG'),
                                                                   (3, 'Informe se possui necessidades especiais', 'Necessidades Especiais');
