CREATE SEQUENCE autor_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE autor (
    id BIGINT DEFAULT nextval('autor_seq') PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    datanascimento DATE NOT NULL
);