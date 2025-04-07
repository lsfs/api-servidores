create sequence seq_unidade_endereco start 1 increment 1;

create table unidade_endereco (
    unid_id    bigserial not null,
    end_id     bigserial not null,
    PRIMARY KEY (unid_id, end_id),
    constraint fk_unidade_endereco foreign key (unid_id) references unidade (unid_id),
    constraint fk_unidade_endereco_endereco foreign key (end_id) references endereco (end_id)
);