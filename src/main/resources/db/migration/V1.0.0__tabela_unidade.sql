create sequence seq_unidade start 1 increment 1;

create table unidade
(
    unid_id         bigserial,
    unid_nome       varchar(200) not null,
    unid_sigla      varchar(20) not null,
    constraint pk_unidade primary key (unid_id)
);