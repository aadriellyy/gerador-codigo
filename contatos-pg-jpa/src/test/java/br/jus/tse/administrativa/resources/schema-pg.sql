CREATE SEQUENCE SQ_PESSOA_FISICA        INCREMENT 1 MINVALUE 1  NO MAXVALUE  START 1 NO CYCLE;
CREATE SEQUENCE SQ_TELEFONE             INCREMENT 1 MINVALUE 1  NO MAXVALUE  START 1 NO CYCLE;
CREATE SEQUENCE SQ_EMAIL                INCREMENT 1 MINVALUE 1  NO MAXVALUE  START 1 NO CYCLE;
CREATE SEQUENCE SQ_ENDERECO             INCREMENT 1 MINVALUE 1  NO MAXVALUE  START 1 NO CYCLE;

CREATE TABLE PESSOA_FISICA (
  ID_PESSOA                  BIGINT     PRIMARY KEY,
  NOME_COMPLETO              varchar(120) NOT NULL,
  DT_NASCIMENTO              date NOT NULL,
  NR_CPF                     varchar(11) UNIQUE NOT NULL
);

CREATE TABLE TELEFONE_CONTATO (
  ID_TELEFONE                BIGINT PRIMARY KEY,
  NR_TELEFONE                varchar(15) NOT NULL,
  FKID_PESSOA                      BIGINT NOT NULL
);

CREATE TABLE EMAIL (
  ID_EMAIL BIGINT                     PRIMARY KEY,
  CX_EMAIL                       varchar(120) NOT NULL,
  FKID_PESSOA                      BIGINT NOT NULL
);

CREATE TABLE ENDERECO (
  ID_ENDERECO                 BIGINT                    PRIMARY KEY,
  CEP                           varchar(10) NOT NULL,
  LOGRADOURO                    varchar(90) NOT NULL,
  COMPLEMENTO                   varchar(30) NOT NULL,
  FKID_PESSOA                     BIGINT UNIQUE NOT NULL
);


CREATE UNIQUE INDEX ON TELEFONE_CONTATO (NR_TELEFONE, FKID_PESSOA);
CREATE UNIQUE INDEX ON EMAIL (CX_EMAIL, FKID_PESSOA);
CREATE UNIQUE INDEX ON ENDERECO (CEP, LOGRADOURO, COMPLEMENTO, FKID_PESSOA);

ALTER TABLE TELEFONE_CONTATO ADD FOREIGN KEY (FKID_PESSOA) REFERENCES PESSOA_FISICA (ID_PESSOA);
ALTER TABLE EMAIL            ADD FOREIGN KEY (FKID_PESSOA) REFERENCES PESSOA_FISICA (ID_PESSOA);
ALTER TABLE ENDERECO         ADD FOREIGN KEY (FKID_PESSOA) REFERENCES PESSOA_FISICA (ID_PESSOA);
