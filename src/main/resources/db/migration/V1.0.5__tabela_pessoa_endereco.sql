create table pessoa_endereco
(
    pes_id         bigserial not null,
    end_id         bigserial not null,
    PRIMARY KEY (pes_id, end_id),
    constraint fk_pessoa_endereco foreign key (pes_id) references pessoa (pes_id),
    constraint fk_pessoa_endereco_endereco foreign key (end_id) references endereco (end_id)
);