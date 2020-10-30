CREATE DATABASE yourcad;

USE yourcad;

CREATE TABLE concessionaria (
concessionaria_id int(11) AUTO_INCREMENT,
concessionaria_cnpj VARCHAR(20),
concessionaria_nome VARCHAR(80),
concessionaria_inscricao_estadual VARCHAR(20),
concessionaria_endereco VARCHAR(40),
concessionaria_bairro VARCHAR(40),
concessionaria_numero_endereco INT(8),
concessionaria_complemento_endereco VARCHAR(50),
concessionaria_cep VARCHAR(12),
concessionaria_cidade VARCHAR(30),
concessionaria_uf VARCHAR(20),
concessionaria_email VARCHAR(30),
concessionaria_site VARCHAR(30),
PRIMARY KEY(concessionaria_id)
);

INSERT INTO concessionaria (
concessionaria_cnpj,
concessionaria_nome,
concessionaria_inscricao_estadual,
concessionaria_endereco,
concessionaria_bairro,
concessionaria_numero_endereco,
concessionaria_complemento_endereco,
concessionaria_cep,
concessionaria_cidade,
concessionaria_uf,
concessionaria_email,
concessionaria_site ) 
VALUES
("03.983.431/0001-03",
"EDP Distribuição de Energia",
"Isento",
"Rua Gomes de Carvalho",
"Jardim Prima velma",
"1996",
"8º Andar",
"04547-006",
"São Paulo",
"SP",
"ri@edpbr.com.br",
"www.edp.com.br");

CREATE TABLE cliente (
cliente_id int(11) AUTO_INCREMENT,
cliente_nome VARCHAR(100),
cliente_apelido VARCHAR(50),
cliente_documento VARCHAR(50),
cliente_endereco VARCHAR(150),
cliente_complemento_endereco VARCHAR(100),
cliente_numero_endereco VARCHAR(20),
cliente_bairro VARCHAR(100),
cliente_cep VARCHAR(20),
cliente_cidade VARCHAR(100),
cliente_uf VARCHAR(50),
PRIMARY KEY(cliente_id)
);

INSERT INTO cliente (
cliente_nome,
cliente_apelido,
cliente_documento,
cliente_endereco,
cliente_complemento_endereco,
cliente_numero_endereco,
cliente_bairro,
cliente_cep,
cliente_cidade,
cliente_uf
)
VALUES
("Mateus de Senne",
"Rogerinho",
"472.244.598-28",
"Rua Pedro Raxixe",
"Não possui",
"18004",
"Jd ReUnião",
"12658-32",
"São José dos Campos",
"São Paulo");


CREATE TABLE instalacao (
	instalacao_id INT(11) NOT NULL AUTO_INCREMENT,
	instalacao_numero VARCHAR(20),
    instalacao_apelido VARCHAR(50),
	instalacao_tipo VARCHAR(20),
	concessionaria_id INT(11),
    cliente_id INT(11),
	PRIMARY KEY(instalacao_id)
);

INSERT INTO instalacao(
instalacao_numero, 
instalacao_apelido, 
instalacao_tipo, 
cliente_id) 
VALUES 
('777',
'77777',
'Agua e Esgoto',
'2');


CREATE TABLE instalacao_agua (
instalacao_id INT(11),
instalacao_agua_hidrometro VARCHAR(20),
instalacao_agua_cod_sabesp VARCHAR(40),
instalacao_agua_cod_cliente VARCHAR(30),
instalacao_agua_economias VARCHAR(255),
instalacao_agua_tipo_ligacao VARCHAR(50),
PRIMARY KEY(instalacao_id)
);


CREATE TABLE instalacao_energia (
instalacao_id int(11),
instalacao_energia_codigo_identificacao VARCHAR(30),
instalacao_energia_codigo_fiscal VARCHAR(30),
instalacao_energia_grupo VARCHAR(30),
instalacao_energia_subgrupo VARCHAR(30),
instalacao_energia_classe VARCHAR(30),
instalacao_energia_tipo_fornecimento VARCHAR(30),
instalacao_energia_modalidade_tarifaria VARCHAR(30),
instalacao_energia_tensao VARCHAR(30),
instalacao_energia_roteiro_leitura VARCHAR(30),
instalacao_energia_medidor VARCHAR(30),
PRIMARY KEY(instalacao_id)
);


*************************************************************************

CREATE TABLE conta_agua (
conta_agua_id int(11) AUTO_INCREMENT,
conta_agua_instalacao_id int(11),
conta_agua_mes VARCHAR(10),
conta_agua_vencimento VARCHAR(20),
conta_agua_data_anterior_leitura VARCHAR(20),
conta_agua_data_atual_leitura VARCHAR(20),
conta_agua_data_previsao_proxima_leitura VARCHAR(20),
conta_agua_valor_anterior VARCHAR(20),
conta_agua_valor_atual_leitura VARCHAR(20),
conta_agua_valor_agua VARCHAR(20),
conta_agua_valor_esgoto VARCHAR(20),
conta_agua_multa VARCHAR(20),
conta_agua_trcf VARCHAR(20),
conta_agua_base_pis_cofins VARCHAR(20),
conta_agua_aliquota_pis_cofins VARCHAR(20),
PRIMARY KEY(conta_agua_id)
);

INSERT INTO conta_agua (
conta_agua_instalacao_id,
conta_agua_mes,
conta_agua_vencimento,
conta_agua_data_anterior_leitura,
conta_agua_data_atual_leitura,
conta_agua_data_previsao_proxima_leitura,
conta_agua_valor_anterior,
conta_agua_valor_atual_leitura,
conta_agua_valor_agua,
conta_agua_valor_esgoto,
conta_agua_multa,
conta_agua_trcf,
conta_agua_base_pis_cofins,
conta_agua_aliquota_pis_cofins)
VALUES
(1,
"09",
"16/09/2020",
"01/08/2020",
"01/09/2020",
"01/10/2020",
"762",
"771",
"27,07",
"27,71",
"",
"0,24",
"48,34",
"6,56");


CREATE TABLE conta_energia (
conta_energia_id INT(11) AUTO_INCREMENT,
conta_energia_instalacao_id int(11),
conta_energia_numero VARCHAR(20),
conta_energia_mes VARCHAR(20),
conta_energia_vencimento VARCHAR(20),
conta_energia_leitura_anterior VARCHAR(20),
conta_energia_leitura_atual VARCHAR(20),
conta_energia_previsao_proxima_leitura VARCHAR(20),
conta_energia_valor_anterior VARCHAR(20),
conta_energia_valor_atual VARCHAR(20),
conta_energia_const_mult VARCHAR(20),
PRIMARY KEY(conta_energia_id)
);

INSERT INTO conta_energia (
conta_energia_instalacao_id,
conta_energia_numero,
conta_energia_mes,
conta_energia_vencimento,
conta_energia_leitura_anterior,
conta_energia_leitura_atual,
conta_energia_previsao_proxima_leitura,
conta_energia_valor_anterior,
conta_energia_valor_atual,
conta_energia_const_mult)
VALUES
(1,
"1505199113",
"09",
"28/09/2020",
"12/08/2020",
"12/09/2020",
"14/10/2020",
"21.614",
"21.89",
"1,00000");































