-- Insere todos os 10 produtos na tabela PRODUTO

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Bolsa de Ombro em Crochê', 'R$ 159,90', 'Uma bolsa de ombro espaçosa, feita à mão com fios de algodão sustentável.', 'imagens/bolsa1.png', 'bolsa');

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Bolsa Redonda de Palha', 'R$ 179,90', 'Com um design clássico e atemporal, esta bolsa redonda de palha é o acessório perfeito para dias de sol.', 'imagens/bolsa2.png', 'bolsa');

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Bolsa Transversal de Couro', 'R$ 149,90', 'Praticidade e estilo se encontram nesta bolsa transversal. Feita com couro sintético de alta qualidade.', 'imagens/bolsa3.png', 'bolsa');

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Bolsa Tote Terracota', 'R$ 129,90', 'Feita à mão com fio trançado em tom terracota, a Bolsa Tote une estilo natural e resistência. Ideal para o dia a dia com um toque artesanal.', 'imagens/bolsa4.png', 'bolsa');

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Bolsa Macramê Madeira', 'R$ 189,90', 'Bolsa artesanal em macramê com alça de madeira e franjas delicadas. Combina charme boho e elegância, perfeita para compor looks leves e sofisticados', 'imagens/bolsa5.png', 'bolsa');

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Colar de Contas de Madeira', 'R$ 89,90', 'Um colar rústico e elegante, feito com contas de madeira natural e um pingente de metal com detalhes turquesa.', 'imagens/colar3.png', 'acessorio');

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Colar Prateado com Pedra Azul', 'R$ 119,90', 'Colar com design trabalhado em metal prateado e uma deslumbrante pedra azul central, perfeito para ocasiões especiais.', 'imagens/colar1.png', 'acessorio');

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Gargantilha de Miçangas', 'R$ 99,90', 'Uma gargantilha delicada e colorida, tecida à mão com miçangas douradas e brancas em um padrão geométrico.', 'imagens/colar2.png', 'acessorio');

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Pulseira de Couro e Pedras', 'R$ 79,90', 'Pulseira artesanal com base de couro e detalhes de pedras verdes e marrons, um toque rústico e natural.', 'imagens/pulseira1.png', 'acessorio');

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Pulseira Dourada com Pedras Verdes', 'R$ 95,90', 'Pulseira elegante com estrutura dourada e pedras verdes translúcidas, perfeita para adicionar um toque de cor.', 'imagens/pulseira2.png', 'acessorio');

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Brinco de Argola Turquesa', 'R$ 85,90', 'Brincos de argola com detalhes em contas de madeira e pedras turquesa, ideais para um visual boho-chic.', 'imagens/brinco1.png', 'acessorio');

INSERT INTO PRODUTO (nome, preco, descricao, imagem, categoria) VALUES 
('Brinco de Penas', 'R$ 75,90', 'Brincos leves e delicados com design de penas em metal e pedras vermelhas, inspirados na natureza.', 'imagens/brinco2.png', 'acessorio');


-- Insere as variações para os produtos

-- Variações para Produto ID 1 (Bolsa de Ombro em Crochê)
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Bege', 'imagens/bolsa1.png', 1);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Preto', 'imagens/bolsa1.png', 1);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Verde Musgo', 'imagens/bolsa1.png', 1);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Azul Marinho', 'imagens/bolsa1.png', 1);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Terracota', 'imagens/bolsa1.png', 1);

-- Variações para Produto ID 2 (Bolsa Redonda de Palha)
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Palha Natural', 'imagens/bolsa2.png', 2);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Preto', 'imagens/bolsa2.png', 2);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Verde Musgo', 'imagens/bolsa2.png', 2);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Azul Marinho', 'imagens/bolsa2.png', 2);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Terracota','imagens/bolsa2.png', 2);

-- Variações para Produto ID 3 (Bolsa Transversal de Couro)
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Marrom', 'imagens/bolsa3.png', 3);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Preto', 'imagens/bolsa3.png', 3);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Verde Musgo', 'imagens/bolsa3.png', 3);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Azul Marinho', 'imagens/bolsa3.png', 3);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Terracota', 'imagens/bolsa3.png', 3);

-- Variações para Produto ID 4 (Bolsa Transversal de Couro)
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Marrom', 'imagens/bolsa4.png', 4);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Preto', 'imagens/bolsa4.png', 4);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Verde Musgo', 'imagens/bolsa4.png', 4);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Azul Marinho', 'imagens/bolsa4.png', 4);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Terracota', 'imagens/bolsa4.png', 4);

-- Variações para Produto ID 5 (Bolsa Macramê Madeira)
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Marrom', 'imagens/bolsa5.png', 5);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Preto', 'imagens/bolsa5.png', 5);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Verde Musgo', 'imagens/bolsa5.png', 5);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Azul Marinho', 'imagens/bolsa5.png', 5);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Terracota', 'imagens/bolsa5.png', 5);

-- Variações para os Acessórios (Produto ID 4 a 10) - Apenas uma variação cada
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Madeira', 'imagens/colar3.png', 4);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Prata', 'imagens/colar1.png', 5);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Dourado', 'imagens/colar2.png', 6);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Couro', 'imagens/pulseira1.png', 7);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Dourado', 'imagens/pulseira2.png', 8);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Turquesa', 'imagens/brinco1.png', 9);
INSERT INTO VARIACAO (cor, imagem, produto_id) VALUES ('Cobre', 'imagens/brinco2.png', 10);



INSERT INTO USUARIOS (id, nome, email, senha) VALUES (1, 'Usuário Teste', 'teste@teste.com', '123');



-- Insere os posts de exemplo na tabela POST

-- Atenção: O tipo da coluna 'conteudo' pode precisar ser ajustado 
-- (ex: usar aspas simples escapadas '' se houver aspas simples no HTML).
-- Para H2, strings longas geralmente funcionam bem com aspas simples.

INSERT INTO POST (titulo, resumo, imagem, data, conteudo) VALUES 
('Sustentabilidade e Estilo: A Combinação Perfeita', 
'Descubra como a moda artesanal pode ser uma poderosa aliada de um estilo de vida mais sustentável...', 
'imagens/blog1.png', 
'15 de Outubro, 2025', 
'<p>O ''slow fashion'' é um movimento que se opõe ao consumo rápido, focando na qualidade em vez da quantidade. Ele valoriza peças duráveis, com história, produzidas de forma artesanal e com materiais que respeitam o meio ambiente.</p><p>Para aderir a essa filosofia, a sugestão é investir em peças-chave de boa qualidade, conhecer a origem dos produtos para apoiar artesãos locais e cuidar bem das roupas para que durem mais. Essencialmente, é um ato de consumo consciente que busca construir um guarda-roupa com mais significado, estilo e que reflita os valores de cada pessoa.</p>'
);

INSERT INTO POST (titulo, resumo, imagem, data, conteudo) VALUES 
('As Cores do Verão: Tendências em Crochê', 
'Veja quais cores estão em alta na estação e como combinar suas bolsas artesanais com os looks da moda...', 
'imagens/blog2.png', 
'10 de Outubro, 2025', 
'<p>O verão chegou e com ele uma explosão de cores. Para a moda artesanal, os tons vibrantes como o laranja, o verde limão e o azul turquesa estão em alta. Essas cores trazem alegria e um toque moderno às peças clássicas de crochê.</p><p>Uma dica de estilo é usar uma bolsa de cor vibrante como ponto de destaque em um look mais neutro, com peças brancas ou em tons de areia. Não tenha medo de ousar e misturar texturas!</p>'
);

INSERT INTO POST (titulo, resumo, imagem, data, conteudo) VALUES 
('Como Cuidar da sua Peça Artesanal', 
'Aprenda dicas valiosas para lavar, secar e guardar suas peças de crochê e palha, garantindo que elas durem por muitos anos...', 
'imagens/blog3.png', 
'05 de Outubro, 2025', 
'<p>Peças feitas à mão requerem um cuidado especial para manterem sua beleza e durabilidade. Para bolsas de crochê, a lavagem deve ser feita à mão, com sabão neutro e água fria. Evite torcer a peça; em vez disso, pressione-a suavemente para remover o excesso de água.</p><p>Deixe secar na sombra, em uma superfície horizontal, para que o peso da água não deforme os pontos. Com esses cuidados simples, sua peça artesanal continuará linda por muitas estações.</p>'
);