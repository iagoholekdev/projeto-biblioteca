CREATE SEQUENCE livro_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE livro (
    id BIGINT DEFAULT nextval('livro_seq') PRIMARY KEY,
    autor_id BIGINT NOT NULL,
    isbn VARCHAR(20),
    statuslivro VARCHAR(50),
    CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES autor(id) ON DELETE CASCADE
);