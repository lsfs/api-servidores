create sequence seq_foto_pessoa;

create table foto_pessoa
(
    fp_id         bigserial,
    pes_id        bigserial not null,
    fp_data       date not null,
    fp_bucket     varchar(50) not null,
    fp_hash       varchar(50) not null,
    constraint pk_foto_pessoa primary key (fp_id),
    constraint fk_foto_pessoa foreign key (pes_id) references pessoa (pes_id)
);