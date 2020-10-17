DROP DATABASE tecsus;
CREATE DATABASE tecsus;
USE tecsus;

CREATE TABLE cli_cliente (
	nome VARCHAR(50) NOT NULL,
    apelido VARCHAR(50) NOT NULL,
    cadastro VARCHAR(14) NOT NULL,
    endereco VARCHAR(30) NOT NULL,
    complemento_endereco VARCHAR(15) DEFAULT NULL,
    numero_endereco INT NOT NULL,
    cep VARCHAR(8) NOT NULL,
    cidade VARCHAR(30) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (cadastro),
    CONSTRAINT uq_apelido UNIQUE (apelido)
);

CREATE TABLE csa_concessionaria (
	nome VARCHAR(70) NOT NULL,
    cadastro VARCHAR(14) NOT NULL,
    inscricao_estadual VARCHAR(12) DEFAULT NULL,
    endereco VARCHAR(30) NOT NULL,
    numero_endereco INT NOT NULL,
    complemento_endereco VARCHAR(20) DEFAULT NULL,
    cep VARCHAR(8) NOT NULL,
    cidade VARCHAR(30) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    email VARCHAR(40) NOT NULL,
    CONSTRAINT pk_concessionaria PRIMARY KEY (cadastro),
    CONSTRAINT uq_inscricao_estadual UNIQUE (inscricao_estadual)
);

CREATE TABLE ins_instalacao (
	numero VARCHAR(10) NOT NULL,
    cadastro_concessionaria VARCHAR(14) NOT NULL,
    apelido VARCHAR(50) NOT NULL,
    cadastro_cliente VARCHAR(14) NOT NULL,
    CONSTRAINT pk_instalacao PRIMARY KEY (numero, cadastro_concessionaria),
    CONSTRAINT fk_instalacaoCliente FOREIGN KEY (cadastro_cliente) REFERENCES cli_cliente (cadastro),
    CONSTRAINT fk_instalacaoConcessionaria FOREIGN KEY (cadastro_concessionaria) REFERENCES csa_concessionaria (cadastro),
    CONSTRAINT uq_apelido UNIQUE (apelido)
);

CREATE TABLE ins_instalacao_energia (
	numero VARCHAR(10) NOT NULL,
	cadastro_concessionaria VARCHAR(14) NOT NULL,
    cod_identificacao INT NOT NULL,
    cod_fiscal_oper INT NOT NULL,
    grupo VARCHAR(1) NOT NULL,
    subgrupo VARCHAR(2) NOT NULL,
    classe VARCHAR(20) NOT NULL,
    tipo_fornecimento VARCHAR(20) NOT NULL,
    modalidade_tarifaria VARCHAR(20) NOT NULL,
    tensao_nominal VARCHAR(8) NOT NULL,
    roteiro_leitura VARCHAR(14) NOT NULL,
    medidor INT NOT NULL,
    CONSTRAINT pk_instalacaoEnergia PRIMARY KEY (numero, cadastro_concessionaria),
    CONSTRAINT fk_instalacaoEnergia FOREIGN KEY (numero, cadastro_concessionaria) 
		REFERENCES ins_instalacao (numero, cadastro_concessionaria)
);

CREATE TABLE ins_instalacao_agua (
	numero VARCHAR(10) NOT NULL,
	cadastro_concessionaria VARCHAR(14) NOT NULL,
    hidrometro VARCHAR(10) NOT NULL,
    cod_sabesp VARCHAR(23),
    cod_cliente VARCHAR(12),
    economias VARCHAR(30),
    tipo_ligacao VARCHAR(15),
	CONSTRAINT pk_instalacaoAgua PRIMARY KEY (numero, cadastro_concessionaria),
    CONSTRAINT fk_instalacaoAgua FOREIGN KEY (numero, cadastro_concessionaria) 
		REFERENCES ins_instalacao (numero, cadastro_concessionaria)
);

CREATE TABLE cnt_conta (
	numero_instalacao VARCHAR(10) NOT NULL,
    cadastro_concessionaria VARCHAR(14) NOT NULL,
	mes DATE NOT NULL,
    vencimento DATE NOT NULL,
    data_anterior_leitura DATE DEFAULT NULL,
    data_atual_leitura DATE NOT NULL,
    data_previsao_prox_leitura DATE NOT NULL,
    valor_anterior_leitura DOUBLE DEFAULT NULL,
    valor_atual_leitura DOUBLE NOT NULL,
    CONSTRAINT pk_conta PRIMARY KEY (numero_instalacao, cadastro_concessionaria, mes),
    CONSTRAINT fk_contaInstalacao FOREIGN KEY (numero_instalacao, cadastro_concessionaria)
		REFERENCES ins_instalacao (numero, cadastro_concessionaria)
);

CREATE TABLE cnt_conta_energia (
	numero_instalacao VARCHAR(10) NOT NULL,
    cadastro_concessionaria VARCHAR(14) NOT NULL,
	mes DATE NOT NULL,
    constante_mult DOUBLE NOT NULL,
    CONSTRAINT pk_contaEnergia PRIMARY KEY (numero_instalacao, cadastro_concessionaria, mes),
    CONSTRAINT fk_contaEnergia FOREIGN KEY (numero_instalacao, cadastro_concessionaria, mes) 
		REFERENCES cnt_conta (numero_instalacao, cadastro_concessionaria, mes)
);

CREATE TABLE cnt_conta_agua (
	numero_instalacao VARCHAR(10) NOT NULL,
    cadastro_concessionaria VARCHAR(14) NOT NULL,
	mes DATE NOT NULL,
    valor_agua DOUBLE DEFAULT NULL,
    valor_esgoto DOUBLE DEFAULT NULL,
    multa DOUBLE DEFAULT NULL,
    trcf DOUBLE DEFAULT NULL,
    base_pis_coffins DOUBLE NOT NULL,
    aliquota_pis_coffins DOUBLE NOT NULL,
	CONSTRAINT pk_contaAgua PRIMARY KEY (numero_instalacao, cadastro_concessionaria, mes),
	CONSTRAINT fk_contaAgua FOREIGN KEY (numero_instalacao, cadastro_concessionaria, mes) 
		REFERENCES cnt_conta (numero_instalacao, cadastro_concessionaria, mes)
);

CREATE TABLE cnt_conta_energia_encargo (
	numero_instalacao VARCHAR(10) NOT NULL,
    cadastro_concessionaria VARCHAR(14) NOT NULL,
    mes DATE NOT NULL,
    cci VARCHAR(5) NOT NULL,
    descricao_produto VARCHAR(20) NOT NULL,
    consumo_kwh DOUBLE NOT NULL,
    tarifa_aplicada DOUBLE NOT NULL,
    valor_fornec DOUBLE NOT NULL,
    tarifa_c_imposto DOUBLE NOT NULL,
    base_icms DOUBLE NOT NULL,
    aliquota_icms DOUBLE NOT NULL,
    base_pis_coffins DOUBLE NOT NULL,
    aliquota_pis DOUBLE NOT NULL,
    aliquota_coffins DOUBLE NOT NULL,
    CONSTRAINT pk_contaEnergiaEncargo PRIMARY KEY (numero_instalacao, cadastro_concessionaria, mes, descricao_produto),
    CONSTRAINT fk_contaEnergiaEncargo FOREIGN KEY (numero_instalacao, cadastro_concessionaria, mes) 
		REFERENCES cnt_conta_energia (numero_instalacao, cadastro_concessionaria, mes)
);

CREATE TABLE cnt_conta_energia_bandeira (
	numero_instalacao VARCHAR(10) NOT NULL,
    cadastro_concessionaria VARCHAR(14) NOT NULL,
    mes DATE NOT NULL,
    cor VARCHAR(10) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
	CONSTRAINT pk_contaEnergiaBandeira PRIMARY KEY (numero_instalacao, cadastro_concessionaria, mes, cor),
    CONSTRAINT fk_contaEnergiaBandeira FOREIGN KEY (numero_instalacao, cadastro_concessionaria, mes) 
		REFERENCES cnt_conta_energia (numero_instalacao, cadastro_concessionaria, mes)
);