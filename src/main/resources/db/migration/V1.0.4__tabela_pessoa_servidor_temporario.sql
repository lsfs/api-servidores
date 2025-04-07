create sequence seq_pessoa start 1 increment 1;

create table pessoa
(
    pes_id         bigserial,
    pes_nome       varchar(200) not null,
    pes_data_nascimento  date,
    pes_sexo      varchar(9) not null,
    pes_mae        varchar(200) not null,
    pes_pai        varchar(200) not null,
    constraint pk_pessoa primary key (pes_id)
);

create sequence seq_servidor_temporario start 1 increment 1;

create table servidor_temporario
(
    st_data_admissao date,
    st_data_demissao    date,
    pes_id         bigserial not null,
    constraint fk_servidor_temporario foreign key (pes_id) references pessoa (pes_id)
);
