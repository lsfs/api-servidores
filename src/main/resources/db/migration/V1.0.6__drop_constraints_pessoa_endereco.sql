ALTER TABLE pessoa_endereco
    DROP CONSTRAINT fk_pessoa_endereco,
    DROP CONSTRAINT fk_pessoa_endereco_endereco;

ALTER TABLE pessoa_endereco
    ADD CONSTRAINT fk_pessoa_endereco_pes_id FOREIGN KEY (pes_id) REFERENCES pessoa (pes_id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_pessoa_endereco_end_id FOREIGN KEY (end_id) REFERENCES endereco (end_id) ON DELETE CASCADE;
