insert into
    usuario(email, senha)
values
    ('admin@email.com', '$2a$10$mNN9aC/qiCw36GVZxpXGJ.3gWJKzdfycB7WuhiNeyJ9vtvxZSlSAu');

insert into
    usuario(email, senha)
values
    ('usuario@email.com', '$2a$10$mNN9aC/qiCw36GVZxpXGJ.3gWJKzdfycB7WuhiNeyJ9vtvxZSlSAu');


CREATE TABLE usuario_roles (
                               usuario_id BIGINT NOT NULL,
                               role VARCHAR(50) NOT NULL,
                               PRIMARY KEY (usuario_id, role),
                               FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

INSERT INTO usuario_roles (usuario_id, role)
VALUES
    ((SELECT id from usuario where email like 'admin@email.com'), 'ADMIN'),
    ((SELECT id from usuario where email like 'usuario@email.com'), 'USUARIO');
