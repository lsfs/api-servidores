ALTER TABLE servidor_temporario
DROP CONSTRAINT fk_servidor_temporario;

ALTER TABLE servidor_temporario
    ADD CONSTRAINT fk_servidor_temporario
        FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id) ON DELETE CASCADE;